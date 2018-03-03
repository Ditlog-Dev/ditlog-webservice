package id.ac.itb.logistik.ditlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DitlogApplication {

  @GetMapping("/")
  public String init() {
    return "Web Service Ditlog API";
  }

  public static void main(String[] args) {
    SpringApplication.run(DitlogApplication.class, args);
  }
}