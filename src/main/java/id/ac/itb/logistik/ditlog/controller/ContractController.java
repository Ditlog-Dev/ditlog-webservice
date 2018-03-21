package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.SPMKContract;
import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import id.ac.itb.logistik.ditlog.model.RoleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

@RestController
public class ContractController {
    @Autowired
    SPMKContractRepository spmkContractRepository;
    Map<Long,String> ROLE = RoleConstant.ROLE;

    @GetMapping("/contracts")
    public ResponseEntity<BaseResponse> getAll(
            HttpServletRequest request,
            @RequestParam(value = "tahun", defaultValue = "0") Long year
    ) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        Long roleId = user.getIdResponsibility();
        Iterable<SPMKContract> result = new Iterable<SPMKContract>() {
            @Override
            public Iterator<SPMKContract> iterator() {
                return null;
            }
        };
        if (ROLE.get(roleId).equals("VENDOR") || ROLE.get(roleId).equals("KASUBDIT_PEMERIKSA")) {
            if(year == 0){
                result = spmkContractRepository.findAll();
            } else {
                result = spmkContractRepository.findAllByYear(year);
            }
        } else if (ROLE.get(roleId).equals("KASIE_PEMERIKSA_BARANG") ||
                        ROLE.get(roleId).equals("PEMERIKSA_BARANG") ||
                        ROLE.get(roleId).equals("KASIE_PEMERIKSA_JASA") ||
                        ROLE.get(roleId).equals("PEMERIKSA_JASA")
                ){
            if(year == 0){
                result = spmkContractRepository.findAllByTag(RoleConstant.TAG.get(roleId));
            } else {
                result = spmkContractRepository.findAllByTagAndYear(RoleConstant.TAG.get(roleId),year);
            }
        }
        if(result.spliterator().getExactSizeIfKnown() == 0){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(result);

        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/contracts/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable("id") Long id) {
        BaseResponse baseResponse = new BaseResponse();
        SPMKContract contract = spmkContractRepository.findContractById(id);
        if(contract == null){
            throw new EntityNotFoundException(SPMKContract.class.getSimpleName());
        }
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(contract);

        return ResponseEntity.ok(baseResponse);
    }
}
