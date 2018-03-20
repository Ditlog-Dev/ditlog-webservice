package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
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
    Map<Long,String> ROLE = RoleConstant.ROLE;

    @GetMapping("/milestone")
    public ResponseEntity<BaseResponse> getAll(HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        Long roleId = user.getIdEmployee();
//        Iterable<Milestone> result = new Iterable<Milestone>() {
//            @Override
//            public Iterator<Milestone> iterator() {
//                return null;
//            }
//        };
//        if (ROLE.get(roleId).equals("VENDOR")) {
//            result = milestoneRepo.findByIdUser(user.getIdUser());
//        } else if (ROLE.get(roleId).equals("PEMERIKSA_JASA")){
//            result = milestoneRepo.findByIdUser();
//        }
//        if(result.spliterator().getExactSizeIfKnown() == 0){
//            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
//        }
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(user);

        return ResponseEntity.ok(baseResponse);
    }
}
