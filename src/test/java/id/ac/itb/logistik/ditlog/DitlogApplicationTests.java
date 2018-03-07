package id.ac.itb.logistik.ditlog;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DitlogApplicationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void contextLoads() {
    String body = this.restTemplate.getForObject("/", String.class);
    Assert.assertEquals(body, "Web Service Ditlog API");
  }
}
