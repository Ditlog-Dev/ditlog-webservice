package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.User;
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
        int statCode;
        if (result == null) {
            statCode = 400;
        }
        else {
            statCode = 200;
        }

        baseResponse.setCode(statCode);
        baseResponse.setPayload(result.getIdUser());
        return ResponseEntity.ok(baseResponse);
    }
}
