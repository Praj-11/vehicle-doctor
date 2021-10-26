package com.handson.springboot.vehicledoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.springboot.vehicledoctor.dao.CustomerRepository;
import com.handson.springboot.vehicledoctor.enitity.Customer;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/")
	public String getHello() {
		String tempString = "Current Supported Endpoints: \n\n "
				+ "/findById/{id}:finds the customer by id  \n "
				+ "{update/{id}: Update the customer's info \n "
				+ "/delete/{id}: Deletes the customer's info \n ";
		
		return tempString;
	}
	/*
	@PostMapping("/save")
	public void save(@RequestBody Customer  cust) {
		customerRepository.save(cust);
	}
	
	@GetMapping("/getall")
	public Iterable<Customer> list() {
		return customerRepository.findAll();
	}
	
	*/
	
	@GetMapping("/findById/{id}")
	public String getCustomer(@PathVariable("id") int id ) {
		return customerRepository.findById(id).toString();
	}
	
	@PutMapping("/update/{id}")
	public void update(@RequestBody Customer cust ) {
		customerRepository.save(cust);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") int id ) {
		customerRepository.deleteById(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

}
