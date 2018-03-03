package id.ac.itb.logistik.ditlog.controller;

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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
            String jwtToken = Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("roles", "user")
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

            baseResponse.setCode(200);
            baseResponse.setPayload(new UserPayload(result.getIdUser(), jwtToken));
        }

        return ResponseEntity.ok(baseResponse);
    }
}
