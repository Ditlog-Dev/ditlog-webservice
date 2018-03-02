package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.Indicator;
import id.ac.itb.logistik.ditlog.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndicatorController {
    @Autowired
    IndicatorRepository indicatorRepository;

    @GetMapping("/indicators")
    public ResponseEntity<BaseResponse> getAll(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setCode(200);
        baseResponse.setPayload(indicatorRepository.findAll());
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/indicators/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable("id") Long id){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setCode(200);
        baseResponse.setPayload(indicatorRepository.findOne(id));
        return ResponseEntity.ok(baseResponse);
    }
}
