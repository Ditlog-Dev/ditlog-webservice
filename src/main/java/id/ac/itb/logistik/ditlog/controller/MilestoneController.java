package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import id.ac.itb.logistik.ditlog.repository.TugasPemeriksaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
public class MilestoneController {
    @Autowired
    MilestoneRepository milestoneRepo;
    @Autowired
    SPMKContractRepository spmkRepo;
    @Autowired
    TugasPemeriksaRepository pemeriksaRepo;

    Map<Long,String> ROLE = RoleConstant.ROLE;

    @GetMapping("/milestone")
    public ResponseEntity<BaseResponse> getAll(HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        Long idResponsibility = user.getIdResponsibility();
        Iterable<SPMKContract> resultsContract = new Iterable<SPMKContract>() {
            @Override
            public Iterator<SPMKContract> iterator() { return null; }
        };
        Iterable<Milestone> resultsMilestone = new Iterable<Milestone>() {
            @Override
            public Iterator<Milestone> iterator() { return null; }
        };
        Iterable<TugasPemeriksa> resultsPemeriksa = new Iterable<TugasPemeriksa>() {
            @Override
            public Iterator<TugasPemeriksa> iterator() { return null; }
        };
        ArrayList<Milestone> results = new ArrayList<Milestone>();

        if (ROLE.get(idResponsibility).equals("VENDOR")) {
            resultsContract = spmkRepo.findByIdVendor(user.getVendorId());
            for (SPMKContract resultContract : resultsContract) {
                resultsMilestone = milestoneRepo.findByIdSPMK(resultContract.getIdSPMK());
                for (Milestone resultMilestone : resultsMilestone) {
                    if (resultMilestone.getStatusRencana().equals("1") &&
                            resultMilestone.getStatusRealisasi() == null)
                        results.add(resultMilestone);
                }
            }
        }
        else if (ROLE.get(idResponsibility).equals("PEMERIKSA_JASA")){
            resultsPemeriksa = pemeriksaRepo.findByIdPemeriksa(user.getIdUser());
            for (TugasPemeriksa resultPemeriksa : resultsPemeriksa) {
                SPMKContract resultContract =
                        spmkRepo.findContractById(resultPemeriksa.getIdKontrak());
                if (resultContract != null) {
                    resultsMilestone = milestoneRepo.findByIdSPMK(resultContract.getIdSPMK());
                    for (Milestone resultMilestone : resultsMilestone) {
                        if (resultMilestone.getStatusRencana().equals("1") &&
                                resultMilestone.getStatusRealisasi() == null)
                            results.add(resultMilestone);
                    }
                }
            }
        }
        if(results.spliterator().getExactSizeIfKnown() == 0){
            throw new EntityNotFoundException(Milestone.class.getSimpleName());
        }

        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(results);

        return ResponseEntity.ok(baseResponse);
    }

    @RequestMapping(value = "/milestone/{id}/{status}", method = RequestMethod.PUT)
    public ResponseEntity<BaseResponse> update(HttpServletRequest request,
                                                @PathVariable("id") Long idProgres,
                                               @PathVariable("status") String status) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setPayload(null);
        User user = (User) request.getAttribute("user");
        if (ROLE.get(user.getIdResponsibility()).equals("PEMERIKSA_JASA")) {
            Milestone milestoneToUpdate = milestoneRepo.findById(idProgres);
            milestoneToUpdate.setStatusRencana(status);
            System.out.println(milestoneToUpdate);
            milestoneRepo.save(milestoneToUpdate);
//            milestoneRepo.updateById(idProgres, status);
            baseResponse.setStatus(true);
            baseResponse.setCode(HttpStatus.OK.value());
        }
        else {
            baseResponse.setStatus(true);
            baseResponse.setCode(HttpStatus.FORBIDDEN.value());
        }
        return ResponseEntity.ok(baseResponse);
    }
}
