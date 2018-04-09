package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
import id.ac.itb.logistik.ditlog.repository.RealisasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class RealisasiController {
    @Autowired
    RealisasiRepository realisasiRepository;

    @Autowired
    MilestoneRepository milestoneRepository;

    Map<Long,String> ROLE = RoleConstant.ROLE;

    @GetMapping("/realisasi/{idSpmk}")
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

            resultsMilestone = realisasiRepository.findByIdSPMK(idSpmk);

            for (Milestone resultMilestone : resultsMilestone) {
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

    @RequestMapping(value = "/realisasi/{idSpmk}", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> insert(HttpServletRequest request,
                                               @RequestBody List<Milestone> listOfRealisasi,
                                               @PathVariable("idSpmk") Long idSpmk) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");

        //validate input
        for (Milestone rencana : listOfRealisasi) {
            if (!rencana.getStatusRencana().equals("1")
                  ||  rencana.getStatusRealisasi() != null) {
                throw new Exception("Wrong input realisasi");
            }
        }

        Iterable<Milestone> resultsMilestone = new Iterable<Milestone>() {
            @Override
            public Iterator<Milestone> iterator() { return null; }
        };

        if (ROLE.get(user.getIdResponsibility()).equals("VENDOR")) {
            try {
                //delete all relevant milestones
                resultsMilestone = realisasiRepository.findByIdSPMK(idSpmk);
                for (Milestone resultMilestone : resultsMilestone) {
                    if (resultMilestone.getStatusRencana().equals("1")
                         && resultMilestone.getStatusRealisasi() == null) {
                        realisasiRepository.delete(resultMilestone);
                    }
                }
                //insert new milestones
                for (Milestone toSave : listOfRealisasi) {
                    Long highestId = milestoneRepository.findHighestID();
                    if (highestId == null)
                        highestId = 0L;
                    toSave.setIdProgres(highestId+1);
                    realisasiRepository.save(toSave);
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
