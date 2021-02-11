package com.utn.PhoneLines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class PhoneLinesApplication  {

	public static void main(String[] args) {

		SpringApplication.run(PhoneLinesApplication.class, args);

	}


}
