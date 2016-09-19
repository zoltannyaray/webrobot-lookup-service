package com.dayswideawake.webrobot.lookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.dayswideawake.webrobot.lookup.messaging.Channels;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(value = Channels.class)
public class WebrobotLookupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebrobotLookupServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
