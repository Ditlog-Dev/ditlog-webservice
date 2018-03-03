package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getAll(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setCode(200);
        baseResponse.setPayload(userRepository.findAll());
        return ResponseEntity.ok(baseResponse);
    }
}
