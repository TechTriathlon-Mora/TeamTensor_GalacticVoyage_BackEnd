package com.teamTensors.planetExploreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.teamTensors")
@EnableEurekaClient
public class PlanetExploreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetExploreServiceApplication.class, args);
	}

}
