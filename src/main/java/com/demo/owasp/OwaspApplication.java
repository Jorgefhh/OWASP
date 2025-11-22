package com.demo.owasp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:owasp.properties")
public class OwaspApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwaspApplication.class, args);
	}

}
