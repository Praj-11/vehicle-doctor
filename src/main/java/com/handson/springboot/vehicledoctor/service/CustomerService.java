package com.handson.springboot.vehicledoctor.service;

import com.handson.springboot.vehicledoctor.enitity.Customer;

public interface CustomerService {

	 public Customer login(String email, String password);

	public Customer register(Customer cust);

}
