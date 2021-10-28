package com.handson.springboot.vehicledoctor.service;

import java.util.List;
import java.util.Optional;

import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;

public interface CustomerService {

	String placeOrder(OrderTable order, Long theId);
	
	Customer findById(Long theId);

	String addCustomer(Customer theCustomer);

	List<OrderTable> findAllOrders(Long theId);

	OrderTable findOrderByTrackingNumber(Long theId, String trackingNumber);

}
