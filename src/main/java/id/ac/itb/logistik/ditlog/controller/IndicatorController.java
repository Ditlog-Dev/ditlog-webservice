package id.ac.itb.logistik.ditlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.Indicator;
import id.ac.itb.logistik.ditlog.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class IndicatorController {
  @Autowired
  IndicatorRepository indicatorRepository;

  @GetMapping("/indicators")
  public ResponseEntity<BaseResponse> getAll(
      @RequestParam(value = "page", defaultValue = "0") String page,
      @RequestParam(value = "limit", defaultValue = "5") String limit
  ) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatus(true);
    baseResponse.setCode(200);
    baseResponse.setPayload(indicatorRepository.findAll(
        new PageRequest(Integer.parseInt(page), Integer.parseInt(limit)))
    );
    return ResponseEntity.ok(baseResponse);
  }

  @GetMapping("/indicators/{id}")
  public ResponseEntity<BaseResponse> getById(@PathVariable("id") Long id) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatus(true);
    baseResponse.setCode(200);
    baseResponse.setPayload(indicatorRepository.findOne(id));
    return ResponseEntity.ok(baseResponse);
  }

  @PostMapping("/indicators")
  public ResponseEntity<BaseResponse> addIndicator(@RequestBody Indicator indicator) {
    BaseResponse baseResponse = new BaseResponse();

    if(indicatorRepository.save(indicator) != null) {
      baseResponse.setStatus(true);
      baseResponse.setCode(200);
      baseResponse.setPayload(indicator);
      return ResponseEntity.ok(baseResponse);
    } else {
      baseResponse.setStatus(true);
      baseResponse.setCode(422);
      return ResponseEntity.ok(baseResponse);
    }
  }
}
