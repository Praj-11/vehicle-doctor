package com.handson.springboot.vehicledoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.service.CustomerService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

	
		@Autowired
		private CustomerService customerService;
		
		@PostMapping("/customer")
		public RedirectView register(@RequestBody Customer customer) {
			Customer tempCustomer = customerService.register(customer); 
			
			if (tempCustomer != null) {
				
				return new RedirectView("/api/customer/");
			}else {
				
				System.err.println("User Already Exist");
			}
			return null;
			
			
		}
	

}
