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

  @LocalServerPort
  private int port;

  private HttpHeaders headers = new HttpHeaders();

  private String requestJson = "{\n" +
      "\t\"name\": \"tes indikator post\",\n" +
      "\t\"idUser\": 33\n" +
      "}";

  @Test
  public void contextLoads() {
    String body = this.restTemplate.getForObject("/", String.class);
    Assert.assertEquals(body, "Web Service Ditlog API");
  }

  @Test
  public void interactionAdd() throws JSONException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/indicators"),
        HttpMethod.POST,
        entity,
        String.class
    );

    // convert to JSON
    JSONObject result = new JSONObject(response.getBody());

    // System.out.println(response.getBody());

    JSONAssert.assertEquals(requestJson,
        result.getJSONObject("payload"),
        false);

  }

  @Test
  public void interactionLoads() throws JSONException {
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/indicators"),
        HttpMethod.GET, entity, String.class);

    System.out.println(response.getBody());

    // convert to JSON
    JSONObject result = new JSONObject(response.getBody());

    // JSON assert
    JSONAssert.assertEquals(
        requestJson,
        result.getJSONObject("payload").getJSONArray("content").getJSONObject(0),
        false
    );

    Assert.assertEquals(200, response.getStatusCodeValue());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

}
