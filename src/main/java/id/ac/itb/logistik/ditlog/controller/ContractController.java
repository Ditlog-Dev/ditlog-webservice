package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.SPMKContract;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class ContractController {
    @Autowired
    SPMKContractRepository spmkContractRepository;

    @GetMapping("/contracts")
    public ResponseEntity<BaseResponse> getAll(
            @RequestParam(value = "tahun", defaultValue = "0") Long year
    ) {
        BaseResponse baseResponse = new BaseResponse();
        Iterable<SPMKContract> result;
        if(year == 0){
            result = spmkContractRepository.findAll();
        } else {
            result = spmkContractRepository.findAllByYear(year);
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
