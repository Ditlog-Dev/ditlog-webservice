package id.ac.itb.logistik.ditlog;

import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.repository.UserRepository;
import id.ac.itb.logistik.ditlog.service.TokenAuthenticationService;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DitlogAuthenticationTests extends BaseTest {

  @Autowired
  UserRepository userRepo;

  private static User testUser;

  private static boolean setUpIsDone = false;

  @Before
  public void setUp() {
    if (setUpIsDone) {
      return;
    }
    testUser = new User();
    testUser.setUsername("john");
    testUser.setPassword(MD5Encrypt("456"));
    testUser.setIdUser(1L);
    testUser.setIdResponsibility(422L);
    userRepo.save(testUser);
    testUser.setPassword("456");

    setUpIsDone = true;
  }


  @Test
  public void whenPostCorrectAuth_thenOK() {
    ResponseEntity<String> response = getStringResponseEntity(testUser);

    JSONObject responseJson;
    try {
      responseJson = new JSONObject(response.getBody());
      Assert.assertEquals(HttpStatus.OK.value(), responseJson.getInt("code"));
    } catch (JSONException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void whenPostCorrectAuth_thenGetCorrectId() {
    ResponseEntity<String> response = getStringResponseEntity(testUser);

    JSONObject responseJson;
    try {
      responseJson = new JSONObject(response.getBody());
      long actualIdUser;
      Object payload = responseJson.optJSONObject("payload");
      if (payload != null) {
        actualIdUser = ((JSONObject) payload).getLong("idUser");
      } else {
        actualIdUser = -1;
      }
      Assert.assertEquals((long) testUser.getIdUser(), actualIdUser);
    } catch (JSONException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void whenPostCorrectAuth_thenGetCorrectRoleId() {
    ResponseEntity<String> response = getStringResponseEntity(testUser);

    JSONObject responseJson;
    try {
      responseJson = new JSONObject(response.getBody());
      long actualRoleId;
      Object payload = responseJson.optJSONObject("payload");
      if (payload != null) {
        actualRoleId = ((JSONObject) payload).getLong("roleId");
      } else {
        actualRoleId = -1;
      }
      Assert.assertEquals((long) testUser.getIdResponsibility(), actualRoleId);
    } catch (JSONException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void whenPostCorrectAuth_thenGetCorrectJwtToken() {
    ResponseEntity<String> response = getStringResponseEntity(testUser);

    String expectedJwtToken = TokenAuthenticationService.getJWT(testUser);

    JSONObject responseJson;
    try {
      responseJson = new JSONObject(response.getBody());
      String actualJwtToken;
      Object payload = responseJson.optJSONObject("payload");
      if (payload != null) {
        actualJwtToken = ((JSONObject) payload).getString("jwtToken");
      } else {
        actualJwtToken = null;
      }
      Assert.assertEquals(expectedJwtToken, actualJwtToken);
    } catch (JSONException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void whenPostWrongAuth_thenError() {
    User wrongUser = new User();
    wrongUser.setUsername("zzzzzzzzzz");
    wrongUser.setPassword("zzzzzzzzzz");

    JSONObject responseJson;
    try {
      ResponseEntity<String> response = getStringResponseEntity(wrongUser);
      responseJson = new JSONObject(response.getBody());
      Assert.assertEquals(HttpStatus.FORBIDDEN.value(), responseJson.getInt("code"));
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void checkMD5Encryption() {
    Assert.assertEquals("202cb962ac59075b964b07152d234b70", MD5Encrypt("123"));
  }

  private ResponseEntity<String> getStringResponseEntity(User user) {
    HttpEntity<User> entity = new HttpEntity<>(user, headers);

    return restTemplate.exchange(
        createURLWithPort("/login"),
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
}
