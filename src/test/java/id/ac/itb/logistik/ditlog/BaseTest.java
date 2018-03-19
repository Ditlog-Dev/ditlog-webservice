package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public abstract class BaseTest {

  @Autowired
  protected TestRestTemplate restTemplate;

  @LocalServerPort
  protected int port;

  protected HttpHeaders headers = new HttpHeaders();

  protected ObjectMapper mapper = new ObjectMapper();

  protected String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
