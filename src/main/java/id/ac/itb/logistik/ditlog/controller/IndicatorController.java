package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndicatorController {
    @Autowired
    IndicatorRepository indicatorRepository;
    /**
     * As an example
     * @return base response
     */
    @GetMapping("/indicators")
    public ResponseEntity<BaseResponse> getAll(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setCode(200);
        baseResponse.setPayload(indicatorRepository.getIndicators());
        return ResponseEntity.ok(baseResponse);
    }
}
