package com.handson.springboot.vehicledoctor.service;

import java.util.*;
import java.util.Optional;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.enitity.SparePart;
import com.handson.springboot.vehicledoctor.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
	
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
			logger.error("No Mechanic Available. Please retry different slot");
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
		logger.info("Everything is good till here: " + order.toString());	
		orderRepository.save(order);
				
		return "Order Tracking Details: " + order.getOrderTrackingNumber();
	}

	@Override
	public String addSpareParts(List<SparePart> spareParts, String theOrderTrackingNumber) {
		
		OrderTable temp = orderRepository.findByOrderTrackingNumber(theOrderTrackingNumber);
		
		if (temp != null && !temp.getStatus().equals('c')) {
			
			logger.info("Spare Parts in Complete Order: " + spareParts.toString());	
			temp.setSparePartsUsed(spareParts);
			logger.info("Order Details on complete order: " + temp);
			
			temp.setStatus('c');
			orderRepository.save(temp);

			return "Order Successfully Completed: \nOrder Desc: " + 
			temp.getOrderDescription() + "\nSpare Parts Used: " + spareParts;
		}
		
		logger.error("Invalid Order Tracking Number");
		return "Invalid Order Tracking Number";
		
	}

	@Override
	public void saveOrder(OrderTable tempOrder) {
		
		orderRepository.save(tempOrder);
		
	}

}
