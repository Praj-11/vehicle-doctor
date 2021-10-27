package com.handson.springboot.vehicledoctor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.handson.springboot.vehicledoctor.dao.CustomerRepository;
import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;

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

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public String update(Customer cust) {
		customerRepository.save(cust);
		return "Customer Updated successfully with id:"+cust.getId();
       
	}
    @Override
	public String deleteById(Long id) {
		Optional<Customer> tempCustomer = customerRepository.findById(id);

		if (tempCustomer.isEmpty()){

			return "Customer not found";
		}

		customerRepository.deleteById(id);

		return "Customer Deleted: " + tempCustomer;
		
	}
	
	
	
}
