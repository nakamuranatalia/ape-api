package br.com.ape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApeApiApplication.class, args);
	}

}
