package com.handson.springboot.vehicledoctor.service;

import java.util.*;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.enitity.SparePart;
import com.handson.springboot.vehicledoctor.repository.GarageRepository;
import com.handson.springboot.vehicledoctor.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private GarageService garageService;
	
	@Override
	public String placeOrder(OrderTable order) {
	
		Optional<Garage> tempGarage = garageService.findByCity(order.getCustomer().getAddress().getCity());
		
		if (tempGarage.isEmpty()) {
			
			return "Services not present in given city. Stay tuned :-)";
		}
		
		Optional<Mechanic> tempMechanic = garageService.findMechanicByAvailability(tempGarage.get().getId(), order.getOrderAppointmentDate());
		
		if (tempMechanic.isEmpty()) {
			
			return "No Mechanic Available. Please try different slot";
		}
		
		order.setOrderTrackingNumber(UUID.randomUUID().toString());
		
		order.setGarage(tempGarage.get());
		order.setMechanic(tempMechanic.get());
		order.setStatus('p');
		order.setBillAmount(0.00);
		order.setPaymentStatus('p');
		order.getOrderAppointmentDate().setHours(order.getOrderAppointmentDate().getHours() + 3);
		order.setSparePartsUsed(new ArrayList<>());
		
		System.out.println("Everything is good till here: " + order.toString());
		
		
		orderRepository.save(order);
		
		
		return "Order Tracking Details: " + order.getOrderTrackingNumber();
	}

	@Override
	public String addSpareParts(List<SparePart> spareParts, String theOrderTrackingNumber) {
		
		OrderTable temp = orderRepository.findByOrderTrackingNumber(theOrderTrackingNumber);
		
		if (temp != null) {
			
			temp.addSparePartsUsed(spareParts);
			
			temp.setStatus('c');
			

			return "Order Successfully Completed: \nOrder Desc: " + 
			temp.getOrderDescription() + "\nSpare Parts Used: " + spareParts;
		}
		
		return "Invalid Order Tracking Number";
		
	}

}
