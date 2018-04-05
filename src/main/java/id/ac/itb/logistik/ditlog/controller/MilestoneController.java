package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import id.ac.itb.logistik.ditlog.repository.TugasPemeriksaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
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

    class Keterangan {
        public String ket;
    }

    @GetMapping("/rencana/{idSpmk}")
    public ResponseEntity<BaseResponse> getByIdSpmk(HttpServletRequest request,
                                                    @PathVariable("idSpmk") Long idSpmk
                                                    ) {
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
            baseResponse.setStatus(false);
            baseResponse.setCode(HttpStatus.FORBIDDEN.value());
            baseResponse.setMessage("UNAUTHORIZED ACCESS");
        }

        return ResponseEntity.ok(baseResponse);
    }

    @RequestMapping(value = "/rencana/{idSpmk}/{status}", method = RequestMethod.PUT)
    public ResponseEntity<BaseResponse> update(HttpServletRequest request,
                                                @RequestBody Keterangan keterangan,
                                                @PathVariable("idSpmk") Long idSpmk,
                                               @PathVariable("status") String status) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");

        if (!status.equals("1") && !status.equals("0")) {
            baseResponse.setStatus(false);
            baseResponse.setMessage("Wrong status, 0 for rejected, 1 for accepted");
            baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(baseResponse);
        }

        if (!Pattern.matches("[a-zA-Z0-9\\s\\-]{1,50}", keterangan.ket)) {
            baseResponse.setStatus(false);
            baseResponse.setMessage("Wrong ketarangan");
            baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(baseResponse);
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
                    resultMilestone.setAlasanReject(keterangan.ket);
                    milestoneRepo.save(resultMilestone);
                }
            }
            baseResponse.setStatus(true);
            baseResponse.setCode(HttpStatus.OK.value());
        }
        else {
            baseResponse.setStatus(false);
            baseResponse.setMessage("Unauthorized access");
            baseResponse.setCode(HttpStatus.FORBIDDEN.value());
        }
        return ResponseEntity.ok(baseResponse);
    }

    @RequestMapping(value = "/rencana/{idSpmk}", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> insert(HttpServletRequest request,
                                               @RequestBody ArrayList<Milestone> listOfRencana,
                                               @PathVariable("idSpmk") Long idSpmk) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");

        //validate input
        for (Milestone rencana : listOfRencana) {
            if (rencana.getStatusRencana() != null ||
                    rencana.getStatusRealisasi() !=                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 null) {
                baseResponse.setStatus(false);
                baseResponse.setMessage("Wrong input rencana");
                baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
            }
        }

        Iterable<Milestone> resultsMilestone = new Iterable<Milestone>() {
            @Override
            public Iterator<Milestone> iterator() { return null; }
        };

        if (ROLE.get(user.getIdResponsibility()).equals("VENDOR")) {
            //delete all relevant milestones
            resultsMilestone = milestoneRepo.findByIdSPMK(idSpmk);
            for (Milestone resultMilestone : resultsMilestone) {
                if (resultMilestone.getStatusRealisasi() == null) {
                    milestoneRepo.delete(resultMilestone);
                }
            }
            //insert new milestones
            milestoneRepo.save(listOfRencana);
            baseResponse.setStatus(true);
            baseResponse.setCode(HttpStatus.OK.value());
        }
        else {
            baseResponse.setStatus(false);
            baseResponse.setMessage("Unauthorized access");
            baseResponse.setCode(HttpStatus.FORBIDDEN.value());
        }
        return ResponseEntity.ok(baseResponse);
    }
}
