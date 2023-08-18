package com.teamTensors.planetExploreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlanetExploreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetExploreServiceApplication.class, args);
	}

}
