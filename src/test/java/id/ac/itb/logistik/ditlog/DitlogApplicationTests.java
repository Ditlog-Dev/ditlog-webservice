package id.ac.itb.logistik.ditlog;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DitlogApplicationTests extends BaseTest {

  @Test
  public void contextLoads() {
    String body = this.restTemplate.getForObject("/", String.class);
    Assert.assertEquals(body, "Web Service Ditlog API");
  }
}
