package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.PenilaianRepository;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PenilaianController {

    @Autowired
    SPMKContractRepository spmkContractRepository;
    @Autowired
    PenilaianRepository penilaianRepository;
    Map<Long,String> ROLE = RoleConstant.ROLE;
    Map<Long,String> TAG = RoleConstant.TAG;

    @GetMapping("/contracts/{id}/indicators")
    public ResponseEntity<BaseResponse> getIndicatorByIdContract(
            HttpServletRequest request,
            @PathVariable("id") Long id
    ) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        Long roleId = user.getIdResponsibility();
        SPMKContract contract = spmkContractRepository.findContractById(id);
        if(!ROLE.get(roleId).equals("KASUBDIT_PEMERIKSA") && !contract.getJenis().equals(TAG.get(roleId))){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        if(contract == null){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        Iterable<PenilaianKinerja> penilaianKinerjaIterable = penilaianRepository.findAllByIdContract(id);
        if(penilaianKinerjaIterable.spliterator().getExactSizeIfKnown() == 0){
            throw new EntityNotFoundException(PenilaianKinerja.class.getSimpleName());
        }
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(penilaianKinerjaIterable);

        return ResponseEntity.ok(baseResponse);
    }


    @PostMapping("/contracts/{id}/indicators")
    public ResponseEntity<BaseResponse> addIndicatorOnContract(
            HttpServletRequest request,
            @PathVariable("id") Long id,
            @RequestBody ArrayList<Long> indicatorIdList
    ) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        Long roleId = user.getIdResponsibility();
        SPMKContract contract = spmkContractRepository.findContractById(id);
        if(!ROLE.get(roleId).equals("KASUBDIT_PEMERIKSA") && !contract.getJenis().equals(TAG.get(roleId))){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        if(contract == null){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        List<PenilaianKinerja> penilaianKinerjaList = new ArrayList<>();
        for (Long indicatorId:indicatorIdList) {
            PenilaianKinerja penilaianKinerja = new PenilaianKinerja(new PenilaianIdentity(id,indicatorId));
            penilaianKinerjaList.add(penilaianKinerja);
        }
        penilaianRepository.save(penilaianKinerjaList);
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(penilaianKinerjaList);

        return ResponseEntity.ok(baseResponse);
    }


    @PutMapping("/contracts/{id}/indicators")
    public ResponseEntity<BaseResponse> updateIndicator(
            HttpServletRequest request,
            @PathVariable("id") Long id,
            @RequestBody ArrayList<PenilaianKinerja> penilaianKinerjaList
    ) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        Long roleId = user.getIdResponsibility();
        SPMKContract contract = spmkContractRepository.findContractById(id);
        if(!ROLE.get(roleId).equals("KASUBDIT_PEMERIKSA") && !contract.getJenis().equals(TAG.get(roleId))){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        if(contract == null){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        List<PenilaianKinerja> penilaianKinerjaUpdateList = new ArrayList<>();
        for (PenilaianKinerja penilaianKinerja : penilaianKinerjaList) {
            PenilaianKinerja penilaianKinerjaUpdate = penilaianRepository.findOne(penilaianKinerja.penilaianIdentity);
            penilaianKinerjaUpdate.setNilai(penilaianKinerja.getNilai());
            penilaianKinerjaUpdateList.add(penilaianKinerjaUpdate);
        }
        penilaianRepository.save(penilaianKinerjaUpdateList);
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(penilaianKinerjaList);

        return ResponseEntity.ok(baseResponse);
    }
}
