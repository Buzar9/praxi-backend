package com.buzar.praxibackend;

import com.buzar.praxibackend.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class PraxiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraxiBackendApplication.class, args);
	}

}
