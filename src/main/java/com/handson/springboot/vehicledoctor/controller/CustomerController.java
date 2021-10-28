package com.handson.springboot.vehicledoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public String helloUser() {
		
		return "Hello Customer";
	}
	
	@GetMapping("/findById/{theId}")
	public String getCustomer(@PathVariable Long theId) {
		
		return customerService.findById(theId).toString();
	}
	
	@PostMapping("/register")
	public String registerCustomer(@RequestBody Customer theCustomer) {
		
		return customerService.addCustomer(theCustomer);
	}
	
	@PostMapping("/{theId}/bookService")
	public String placeOrder(@RequestBody OrderTable order, @PathVariable("theId") Long theId) {
		
		return customerService.placeOrder(order, theId);
		
	}
	
	@GetMapping("/{theId}/orders")
	public String findAllOrders(@PathVariable("theId") Long theId) {
		List<OrderTable> temp = customerService.findAllOrders(theId);
		return temp.toString();			
	}
	
	@GetMapping("/{theId}/orders/trackingOrderId/{theOrderTrackingNumber}") 
		public String findOrderByTrackingId(@PathVariable("theId") Long theId,@PathVariable("theOrderTrackingNumber") String trackingNumber) {
			return customerService.findOrderByTrackingNumber(theId,trackingNumber).toString();
		}
		
		
	
}
