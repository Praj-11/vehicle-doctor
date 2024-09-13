package com.handson.springboot.vehicledoctor;

import org.springframework.boot.SpringApplication;
import org.modelmapper.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VehicledoctorApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VehicledoctorApplication.class, args);
	}

}
