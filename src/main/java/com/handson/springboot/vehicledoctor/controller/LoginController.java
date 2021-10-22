package com.handson.springboot.vehicledoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.service.GarageService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private GarageService garageService;
	
	@GetMapping("/garage")
	public RedirectView login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		
		Garage tempGarage = garageService.login(email, password); 
				
		if (tempGarage != null) {
			
			return new RedirectView("/api/garage/");
		}else {
			
			System.err.println("Invalid Login Credentials");
		}
		return null;
	}
}
