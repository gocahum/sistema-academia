package edu.uday.sie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@PropertySource("classpath:application.properties")
//@EnableAsync
public class SieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SieApiApplication.class, args);
	}

}
