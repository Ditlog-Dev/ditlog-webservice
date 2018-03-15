package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.model.UserPayload;
import id.ac.itb.logistik.ditlog.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.naming.AuthenticationException;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepo;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> loginUser(@RequestBody User user) throws Exception {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            throw new IllegalArgumentException("Username / password is empty");
        }
        try{
            user.setPassword(encode(user.getPassword()));
        } catch (Exception e) {
            throw new SecurityException("Failed to encode");
        }
        User result = userRepo.findUserByUsernamePassword(user.getUsername(), user.getPassword());

        BaseResponse baseResponse = new BaseResponse();

        if (result == null) {
            throw new AuthenticationException("Wrong username / password");
        } else {
            String jwtToken = Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("roles", "user")
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

            baseResponse.setStatus(true);
            baseResponse.setCode(HttpStatus.ACCEPTED.value());
            baseResponse.setPayload(new UserPayload(result.getIdUser(), result.getIdEmployee(), jwtToken));
        }

        return ResponseEntity.ok(baseResponse);
    }

    private String encode(String in) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        if (in == null) {
            return null;
        }
        try {
            md.update(in.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
                    .printHexBinary(digest).toLowerCase();
            return myHash;
        } catch (Exception se) {
            throw new Exception("Exception while encoding " + se);
        }

    }
}
