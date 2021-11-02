package com.handson.springboot.vehicledoctor.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.repository.MechanicRepository;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.enitity.SparePart;
import com.handson.springboot.vehicledoctor.exceptions.ApiRequestException;

@Service
public class MechanicServiceImpl implements MechanicService{

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired 
	private OrderService orderService;
	
	@Autowired
	private SparePartService sparePartService;
	
	@Override
	public Long addMechanic(Mechanic theMechanic) {
		
		return mechanicRepository.save(theMechanic).getId();
	}
	
	@Override
	public Mechanic login(String email, String password) {
		
		if (!mechanicRepository.findByEmail(email).isEmpty()) {
			
			
			Mechanic tempMechanic = mechanicRepository.findByEmail(email).get(0);
			
			System.out.println("Mechanic Details: " + tempMechanic);
			
			
			if (tempMechanic.getPassword().equals(password)) {
				
				return tempMechanic;
			}
		}
		
		throw new ApiRequestException("Invalid Login Credentials");
	}

	@Override
	public void deleteMechanic(Long theId) {

		mechanicRepository.deleteById(theId);
	}

	@Override
	public Optional<Mechanic> findById(Long theId) {
		return mechanicRepository.findById(theId);
	}

	@Override
	public boolean checkEmail(String emailString) {

		return mechanicRepository.findByEmail(emailString).isEmpty();
	}

	@Override
	public boolean existsById(Long theId, Mechanic theMechanic) {
		
		Optional<Mechanic> tempMechanic = mechanicRepository.findById(theMechanic.getId());
		
		if (tempMechanic.isEmpty()) {
			
			throw new ApiRequestException("Invalid Mechanic Id");
		}
		
		Long tempId = tempMechanic.get().getEmployer().getId();
		
		if(Objects.equals(tempId, theId)) {
			
			theMechanic.getAddress().setId(tempMechanic.get().getAddress().getId());
			return true;
		}
		
		return false;
		
	}

	@Override
	public String findAllMechanic() {
		
			return mechanicRepository.findAll().toString();
	}


	@Override
	public String taskCompleted(List<SparePart> spareParts, String theOrderTrackingNumber) {
		
		List<SparePart> tempSpareParts = sparePartService.getSpareParts(spareParts);
		
		return orderService.addSpareParts(tempSpareParts, theOrderTrackingNumber);
	}

	@Override
	public List<OrderTable> findPendingOrders(Long theMechanicId) {
		
		if (!mechanicRepository.existsById(theMechanicId)) {
			
			throw new ApiRequestException("Invalid Mechanic Id");
		}
		
		return mechanicRepository.getById(theMechanicId).getOrders().stream()
				.filter(temp -> temp.getStatus().equals('p')).collect(Collectors.toList());
		
	}

	

	


}
