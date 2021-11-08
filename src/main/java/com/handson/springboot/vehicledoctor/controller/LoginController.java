package com.handson.springboot.vehicledoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.exceptions.ApiRequestException;
import com.handson.springboot.vehicledoctor.service.CustomerService;
import com.handson.springboot.vehicledoctor.service.GarageService;
import com.handson.springboot.vehicledoctor.service.MechanicService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private GarageService garageService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MechanicService mechanicService;
	
	private static final String INVALIDLOGINCREDENTIAL = "Invalid Login Credentials";
	
	@GetMapping("/garage")
	public RedirectView login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		
		Garage tempGarage = garageService.login(email, password); 
				
		if (tempGarage != null) {
			
			return new RedirectView("/api/garage/" + tempGarage.getId());
		}else {
			
			throw new ApiRequestException(INVALIDLOGINCREDENTIAL);
		}
	}
	
	@GetMapping("/customer")
	public RedirectView login1(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		
		Customer tempCustomer = customerService.login(email, password); 
				
		if (tempCustomer != null) {
			
			return new RedirectView("/api/customer/" + tempCustomer.getId());
		}else {

			throw new ApiRequestException(INVALIDLOGINCREDENTIAL);
		}
	}
	
	@GetMapping("/mechanic")
	public RedirectView login2(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		
		Mechanic tempMechanic = mechanicService.login(email, password); 
				
		if (tempMechanic != null) {
			
			return new RedirectView("/api/mechanic/" + tempMechanic.getId());
		}else {

			throw new ApiRequestException(INVALIDLOGINCREDENTIAL);
		}
	}
}
