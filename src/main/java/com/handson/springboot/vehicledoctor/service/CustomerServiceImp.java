package com.handson.springboot.vehicledoctor.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.handson.springboot.vehicledoctor.dao.CustomerRepository;
import com.handson.springboot.vehicledoctor.enitity.Customer;

public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer login(String email, String password) {
		
		if (!customerRepository.findByEmail(email).isEmpty()) {
			Customer tempCustomer = customerRepository.findByEmail(email).get(0);
			return (tempCustomer.getPassword().equals(password)) ? tempCustomer : null;
			
		}
		return null;
	}
	
	@Override
	public Customer register(Customer cust) {
		
		if (customerRepository.findByEmail(cust.getEmailId()).isEmpty()) {
			
			customerRepository.save(cust);
		    return cust;
			
		}
		
		return null;
	}
	
	
	
}
