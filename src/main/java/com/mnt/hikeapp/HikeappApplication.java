package com.mnt.hikeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class HikeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HikeappApplication.class, args);
	}

}
