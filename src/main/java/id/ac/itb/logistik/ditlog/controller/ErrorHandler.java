package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

  public ErrorHandler() {
    super();
  }

  /**
   * General exception. Throw all undefined exception.
   */
  @ExceptionHandler({ Exception.class })
  public ResponseEntity<BaseResponse> handleGeneralException(Exception ex) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatus(false);
    baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
    baseResponse.setMessage(ex.getClass().getSimpleName() + " : " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(baseResponse);
  }

  /**
   * Handling not found for all entity.
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<BaseResponse> handleNotFoundException(RuntimeException ex) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatus(false);
    baseResponse.setCode(HttpStatus.NOT_FOUND.value());
    baseResponse.setMessage(ex.getClass().getSimpleName() + " : " + ex.getMessage() + " not found");
    return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(baseResponse);
  }

  /**
   * Handling authentication exception. If username or password didn't match. Or not found in db.
   */
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<BaseResponse> handleAuthException(AuthenticationException ex) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatus(false);
    baseResponse.setCode(HttpStatus.UNAUTHORIZED.value());
    baseResponse.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(baseResponse);
  }

}

