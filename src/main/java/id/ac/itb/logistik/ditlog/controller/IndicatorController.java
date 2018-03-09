package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.Indicator;
import id.ac.itb.logistik.ditlog.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndicatorController {

  @Autowired
  IndicatorRepository indicatorRepository;

  @GetMapping("/indicators")
  public ResponseEntity<BaseResponse> getAll(
      @RequestParam(value = "page", defaultValue = "0") String page,
      @RequestParam(value = "limit", defaultValue = "5") String limit,
      @RequestParam(value = "sort", defaultValue = "id") String sort,
      @RequestParam(value = "dir", defaultValue = "asc") String direction) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatus(true);
    baseResponse.setCode(200);
    baseResponse.setPayload(
        indicatorRepository.findAll(
            new PageRequest(
                Integer.parseInt(page),
                Integer.parseInt(limit),
                direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                sort)));
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

    Indicator result = indicatorRepository.save(indicator);

    if (result != null) {
      baseResponse.setStatus(true);
      baseResponse.setCode(200);
      baseResponse.setPayload(result);
    } else {
      baseResponse.setStatus(true);
      baseResponse.setCode(422);
      baseResponse.setMessage("Unprocessable entity. Check the JSON structure.");
    }

    return ResponseEntity.ok(baseResponse);
  }
}
