package id.ac.itb.logistik.ditlog.controller;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import id.ac.itb.logistik.ditlog.repository.TugasPemeriksaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.*;
import java.util.regex.Pattern;

@RestController
public class MilestoneController {
    @Autowired
    MilestoneRepository milestoneRepo;
    @Autowired
    SPMKContractRepository spmkRepo;
    @Autowired
    TugasPemeriksaRepository pemeriksaRepo;

    Map<Long,String> ROLE = RoleConstant.ROLE;

    static public class Keterangan {
        public String ket;

        public String getKet() {
            return ket;
        }

        public void setKet(String ket) {
            this.ket = ket;
        }
    }

    @GetMapping("/rencana/{idSpmk}")
    public ResponseEntity<BaseResponse> getByIdSpmk(HttpServletRequest request,
                                                    @PathVariable("idSpmk") Long idSpmk
                                                    ) throws AuthenticationException {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        ArrayList<Milestone> results = new ArrayList<Milestone>();
        Iterable<Milestone> resultsMilestone = new Iterable<Milestone>() {
            @Override
            public Iterator<Milestone> iterator() { return null; }
        };

        if (ROLE.get(user.getIdResponsibility()).equals("PEMERIKSA_JASA")
                || ROLE.get(user.getIdResponsibility()).equals("VENDOR")) {

            resultsMilestone = milestoneRepo.findByIdSPMK(idSpmk);

            for (Milestone resultMilestone : resultsMilestone) {
                if (resultMilestone.getStatusRealisasi() == null)
                    results.add(resultMilestone);
            }
            baseResponse.setStatus(true);
            baseResponse.setCode(HttpStatus.OK.value());
            baseResponse.setPayload(results);
        }
        else {
            throw new AuthenticationException("Unauthorized Access");
        }

        return ResponseEntity.ok(baseResponse);
    }

    @RequestMapping(value = "/rencana/{idSpmk}/{status}", method = RequestMethod.PUT)
    public ResponseEntity<BaseResponse> update(HttpServletRequest request,
                                                @RequestBody(required=false) Keterangan keterangan,
                                                @PathVariable("idSpmk") Long idSpmk,
                                               @PathVariable("status") String status) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");

        if (!status.equals("1") && !status.equals("0")) {
            throw new Exception("Wrong status, 0 for rejected, 1 for accepted");
        }

        Iterable<Milestone> resultsMilestone = new Iterable<Milestone>() {
            @Override
            public Iterator<Milestone> iterator() { return null; }
        };

        if (ROLE.get(user.getIdResponsibility()).equals("PEMERIKSA_JASA")) {
            resultsMilestone = milestoneRepo.findByIdSPMK(idSpmk);
            for (Milestone resultMilestone : resultsMilestone) {
                if (resultMilestone.getStatusRealisasi() == null) {
                    resultMilestone.setStatusRencana(status);
                    if (keterangan != null)
                        resultMilestone.setAlasanReject(keterangan.ket);
                    milestoneRepo.save(resultMilestone);
                }
            }
            baseResponse.setStatus(true);
            baseResponse.setCode(HttpStatus.OK.value());
        }
        else {
            throw new AuthenticationException("Unauthorized Access");
        }
        return ResponseEntity.ok(baseResponse);
    }

    @RequestMapping(value = "/rencana/{idSpmk}", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> insert(HttpServletRequest request,
                                               @RequestBody Milestone[] listOfRencana,
                                               @PathVariable("idSpmk") Long idSpmk) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");

        //validate input
        for (Milestone rencana : listOfRencana) {
            if (rencana.getStatusRencana() != null ||
                    rencana.getStatusRealisasi() != null) {
                throw new Exception("Wrong input rencana");
            }
        }

        Iterable<Milestone> resultsMilestone = new Iterable<Milestone>() {
            @Override
            public Iterator<Milestone> iterator() { return null; }
        };

        if (ROLE.get(user.getIdResponsibility()).equals("VENDOR")) {
            try {
                //delete all relevant milestones
                resultsMilestone = milestoneRepo.findByIdSPMK(idSpmk);
                for (Milestone resultMilestone : resultsMilestone) {
                    if (resultMilestone.getStatusRealisasi() == null) {
                        milestoneRepo.delete(resultMilestone);
                    }
                }
                //insert new milestones
                for (Milestone toSave : listOfRencana) {
                    Long highestId = milestoneRepo.findHighestID();
                    if (highestId == null)
                        highestId = 0L;
                    toSave.setIdProgres(highestId+1);
                    milestoneRepo.save(toSave);
                }
                baseResponse.setStatus(true);
                baseResponse.setCode(HttpStatus.OK.value());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            throw new AuthenticationException("Unauthorized Access");
        }
        return ResponseEntity.ok(baseResponse);
    }
}
