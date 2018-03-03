package id.ac.itb.logistik.ditlog.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.model.UserPayload;
import id.ac.itb.logistik.ditlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepo;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> loginUser(@RequestBody User user){
        System.out.println(user.getUsername() + " " + user.getPassword());

        User result = userRepo.findUserByUsernamePassword(user.getUsername(), user.getPassword());

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);

        if (result == null) {
            baseResponse.setCode(400);
            baseResponse.setPayload("Wrong username/password");
        }
        else {
            baseResponse.setCode(200);
            baseResponse.setPayload(new UserPayload(result.getIdUser(), "m2kd2kmd1"));
        }


        return ResponseEntity.ok(baseResponse);
    }
}
