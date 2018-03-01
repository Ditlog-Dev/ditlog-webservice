package id.ac.itb.logistik.ditlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DitlogApplication {

	@RequestMapping("/")
	public String home() {
			return "Hello Docker Worldsss";
	}

	public static void main(String[] args) {
		SpringApplication.run(DitlogApplication.class, args);
	}
}