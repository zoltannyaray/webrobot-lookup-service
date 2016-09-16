package com.dayswideawake.webrobot.lookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebrobotLookupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebrobotLookupServiceApplication.class, args);
	}
}
