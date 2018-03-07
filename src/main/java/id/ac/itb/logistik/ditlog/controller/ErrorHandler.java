package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<BaseResponse> handleGeneralException(Exception ex) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatus(false);
    baseResponse.setCode(500);
    baseResponse.setMessage(ex.getMessage());
    return ResponseEntity.badRequest().body(baseResponse);
  }
}
