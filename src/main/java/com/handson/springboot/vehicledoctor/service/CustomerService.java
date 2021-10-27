package com.handson.springboot.vehicledoctor.service;

import java.util.Optional;

import com.handson.springboot.vehicledoctor.enitity.Customer;

public interface CustomerService {

	 public Customer login(String email, String password);

	public Customer register(Customer cust);
	
	public Optional<Customer> findById(Long id);

	public String update(Customer cust);

	public String deleteById(Long id);

}
