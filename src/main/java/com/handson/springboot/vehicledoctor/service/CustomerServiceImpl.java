package com.handson.springboot.vehicledoctor.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.exceptions.ApiRequestException;
import com.handson.springboot.vehicledoctor.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderService orderService;
	
	private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);

	private static final String INVALIDCUSTOMERID = "Invalid Customer Id";

	@Override
	public Customer login(String email, String password) {
		
		if (!customerRepository.findByEmail(email).isEmpty()) {
			Customer tempCustomer = customerRepository.findByEmail(email).get(0);
			return (password.equals(tempCustomer.getPassword())) ? tempCustomer : null;
			
		}
		return null;
	}
	
	@Override
	public String update(Customer theCustomer) {
		
		
		
		customerRepository.save(theCustomer);
		logger.info("Customer updated successfully with id:"+ theCustomer.getId());
		return "Customer Updated successfully with id:" + theCustomer.getId();
       
	}

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
		
		Optional<Customer> tempCustomer = customerRepository.findById(theId);
		
		if (tempCustomer.isEmpty()) {
			
			throw new ApiRequestException(INVALIDCUSTOMERID);
		}
		
		return tempCustomer.get();
	}

	@Override
	public String addCustomer(Customer theCustomer) {
		
		logger.info("Customer Registered Successfully !!!");
		return customerRepository.save(theCustomer).toString();
	}

	@Override
	public List<OrderTable> findAllOrders(Long theId) {
	
		Optional<Customer> tempCustomer = customerRepository.findById(theId);
		
		if (tempCustomer.isEmpty()) {
			throw new ApiRequestException(INVALIDCUSTOMERID);
		}
		
		return tempCustomer.get().getOrders();
	}

	@Override
	public OrderTable findOrderByTrackingNumber(Long theId, String trackingNumber) {
		
		if (!customerRepository.existsById(theId)) {

			throw new ApiRequestException(INVALIDCUSTOMERID);
		}
		
		Optional<OrderTable> temp = customerRepository.getById(theId).getOrders().stream()
				.filter(temp2 -> temp2.getOrderTrackingNumber().equals(trackingNumber)).findFirst();
		
		if (temp.isEmpty()) {
			logger.error("Invalid Tracking Number");
			throw new ApiRequestException("Invalid Tracking Number");			
		}
		
		return temp.get();
	}

	@Override
	public String payOrder(Long theId, String theOrderTrackingNumber) {
		
		if (!customerRepository.existsById(theId)) {

			throw new ApiRequestException(INVALIDCUSTOMERID);
		}
		
		Optional<OrderTable> tempOrder = customerRepository.getById(theId).getOrders().stream()
				.filter(temp -> temp.getOrderTrackingNumber().equals(theOrderTrackingNumber)).findFirst();
		
		if (tempOrder.isEmpty()) {
			

			throw new ApiRequestException("Order doesn't exist, please provide valid Order Tracking Number");
		}
		
		tempOrder.get().setPaymentStatus('c');
		
		if (tempOrder.get().getSparePartsUsed().isEmpty()) {
			
			throw new ApiRequestException("Please allow mechanic to first complete the order");
		}
		
		Double totalAmount = tempOrder.get().getSparePartsUsed().stream().
				reduce(0.00, (temp, 
						tempSparePart) -> temp + tempSparePart.getPrice(), 
						Double::sum);
		
		tempOrder.get().setBillAmount(totalAmount);
		
		tempOrder.get().setOrderCompleted(new Date());
		
		orderService.saveOrder(tempOrder.get());
		
		
		return "Order Invoice \nCustomer Name:  " + tempOrder.get().getCustomer().getName() + 
				"\nCustomerAddress: " + tempOrder.get().getCustomer().getAddress() + 
				"\nCar Details: " + tempOrder.get().getCustomer().getCarDetails() + 
				"\nGarage Name: " + tempOrder.get().getGarage().getGarageName() + 
				"\nGarage Contact Number: " + tempOrder.get().getGarage().getPhoneNumber() + 
				"\nMechanic Name: " + tempOrder.get().getMechanic().getName() + 
				"\nSpare Part Details: " + tempOrder.get().getSparePartsUsed().toString() +
				"\nTotal Amount: " + totalAmount;
		
	}
	
}
