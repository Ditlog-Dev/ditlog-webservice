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
			return "Hello Docker World";
	}

	public static void main(String[] args) {
		Integer a = null;
		System.out.printf(a.toString());
		SpringApplication.run(DitlogApplication.class, args);
	}
}