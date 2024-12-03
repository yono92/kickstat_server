package com.yono.kickstat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KickstatApplication {
	public static void main(String[] args) {
		SpringApplication.run(KickstatApplication.class, args);
	}

}
