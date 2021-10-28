package com.handson.springboot.vehicledoctor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.dao.CustomerRepository;
import com.handson.springboot.vehicledoctor.dao.OrderRepository;
import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
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

	@Override
	public List<OrderTable> findAllOrders(Long theId) {
		// TODO Auto-generated method stub
	
		return customerRepository.existsById(theId)?customerRepository.findById(theId).get().getOrders():new ArrayList<OrderTable>();
	}

	@Override
	public OrderTable findOrderByTrackingNumber(Long theId, String trackingNumber) {
		// TODO Auto-generated method stub
		OrderTable temp = orderRepository.existsById(theId)?orderRepository.findByOrderTrackingNumber(trackingNumber):null;
	
		if(temp != null)
			return temp.getCustomer().getId() == theId ? temp : null;
		 
		return null;
	}
	
	
}
