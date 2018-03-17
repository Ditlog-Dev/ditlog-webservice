package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {
    @Autowired
    SPMKContractRepository spmkContractRepository;

    @GetMapping("/contracts")
    public ResponseEntity<BaseResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "limit", defaultValue = "5") String limit,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "dir", defaultValue = "asc") String direction) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(
                spmkContractRepository.findAll(
                        new PageRequest(
                                Integer.parseInt(page),
                                Integer.parseInt(limit),
                                direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                                sort)));
        return ResponseEntity.ok(baseResponse);
    }
}
