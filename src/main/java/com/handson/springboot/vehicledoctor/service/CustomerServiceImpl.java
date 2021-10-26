package com.handson.springboot.vehicledoctor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.dao.CustomerRepository;
import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderService orderService;

	@Override
	public String placeOrder(OrderTable order, Long theId) {
		
		order.setCustomer(findById(theId));
		
		String temp = orderService.placeOrder(order);
		
		if (order.getGarage() == null || order.getMechanic() == null) {
			
			order.setCustomer(null);
		}
		
		return temp;
	}

	@Override
	public Customer findById(Long theId) {
		
		return customerRepository.findById(theId).get();
	}

	@Override
	public String addCustomer(Customer theCustomer) {
		
		return customerRepository.save(theCustomer) != null ? "You have registered successfully!" : "Invalid Entries please try again";
	}
	
	
}
