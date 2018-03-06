package id.ac.itb.logistik.ditlog;

import id.ac.itb.logistik.ditlog.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DitlogUserServiceTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    private static final String API_ROOT = "http://localhost:8080/user";

    private ResponseEntity<String> getStringResponseEntity(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(
                API_ROOT,
                HttpMethod.POST, entity, String.class
        );
    }

    private String MD5Encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter
                    .printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void whenPostCorrectAuth_thenOK() {
        User user = new User();
        user.setUsername("aep");
        user.setPassword(MD5Encrypt("123"));

        ResponseEntity<String> response = getStringResponseEntity(user);

        JSONObject responseJson;
        try {
            responseJson = new JSONObject(response.getBody());
            Assert.assertEquals(HttpStatus.OK.value(),responseJson.getInt("code"));
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void whenPostCorrectAuth_thenGetCorrectId() {
        User user = new User();
        user.setUsername("aep");
        user.setPassword(MD5Encrypt("123"));

        ResponseEntity<String> response = getStringResponseEntity(user);

        JSONObject responseJson;
        try {
            responseJson = new JSONObject(response.getBody());
            int actualIdUser;
            Object payload = responseJson.optJSONObject("payload");
            if(payload != null){
                actualIdUser = ((JSONObject)payload).getInt("idUser");
            } else {
                actualIdUser = -1;
            }
            Assert.assertEquals(2,actualIdUser);
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void whenPostCorrectAuth_thenGetCorrectJwtToken() {
        User user = new User();
        user.setUsername("aep");
        user.setPassword(MD5Encrypt("123"));

        ResponseEntity<String> response = getStringResponseEntity(user);

        String expectedJwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", "user")
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        JSONObject responseJson;
        try {
            responseJson = new JSONObject(response.getBody());
            String actualJwtToken;
            Object payload = responseJson.optJSONObject("payload");
            if(payload != null){
                actualJwtToken = ((JSONObject)payload).getString("jwtToken");
            } else {
                actualJwtToken = null;
            }
            Assert.assertEquals(expectedJwtToken,actualJwtToken);
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void whenPostWrongAuth_thenError() {
        User user = new User();
        user.setUsername("zzzzzzzzzz");
        user.setPassword("zzzzzzzzzz");

        ResponseEntity<String> response = getStringResponseEntity(user);

        JSONObject responseJson;
        try {
            responseJson = new JSONObject(response.getBody());
            Assert.assertEquals(400,responseJson.getInt("code"));
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void checkMD5Encryption() {
        Assert.assertEquals("202cb962ac59075b964b07152d234b70", MD5Encrypt("123"));
    }
}
