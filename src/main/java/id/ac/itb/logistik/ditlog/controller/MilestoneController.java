package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

@RestController
public class MilestoneController {
    @Autowired
    MilestoneRepository milestoneRepo;
    @Autowired
    SPMKContractRepository spmkRepo;

    Map<Long,String> ROLE = RoleConstant.ROLE;

    @GetMapping("/milestone")
    public ResponseEntity<BaseResponse> getAll(HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        Long idResponsibility = user.getIdResponsibility();
        Iterable<SPMKContract> results = new Iterable<SPMKContract>() {
            @Override
            public Iterator<SPMKContract> iterator() {
                return null;
            }
        };
        if (ROLE.get(idResponsibility).equals("VENDOR")) {
            results = spmkRepo.findByIdVendor(user.getIdVendor());
        }
//        else if (ROLE.get(idResponsibility).equals("PEMERIKSA_JASA")){
//            result = milestoneRepo.findByIdUser();
//        }
        if(results.spliterator().getExactSizeIfKnown() == 0){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(results);

        return ResponseEntity.ok(baseResponse);
    }
}
