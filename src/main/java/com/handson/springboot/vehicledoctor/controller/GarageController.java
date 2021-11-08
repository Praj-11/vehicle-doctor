package com.handson.springboot.vehicledoctor.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.handson.springboot.vehicledoctor.dto.MechanicDTO;
import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.exceptions.ApiRequestException;
import com.handson.springboot.vehicledoctor.service.GarageService;

@RestController
@RequestMapping("/api/garage")
public class GarageController {
	
	@Autowired
	private GarageService garageService;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	public String getHello() {
		
		return "Current Supported Endpoints: \n\n "
				+ "/findById/{theId}: Shows garage details \n "
				+ "/{theId}/addMechanic: Add Mechanic \n "
				+ "/{theId}/updateMechanic: Update Mechanic Info \n "
				+ "/{theGarageId}/deleteMechanic/{theMechanicId}: Delete Mechanic Details \n"
				+ "/{theId}/orders: Get all orders";
	}
	
	@GetMapping("/{theId}")
	public String helloUser(@PathVariable Long theId) {
		
		
		
		Optional<Garage> tempGarageOptional = garageService.getGarageOwner(theId);
		
		if (tempGarageOptional.isEmpty()) {
			
			throw new ApiRequestException("Invalid Garage Id");
		}
		
		Garage tempGarage = tempGarageOptional.get();
		
		return "Hello " + tempGarage.getGarageName() + "(id: " + tempGarage.getId() + ")\n\n"
				+ "Current Supported Endpoints: \n\n "
				+ "/findById/{theId}: Shows garage details \n "
				+ "/{theId}/addMechanic: Add Mechanic \n "
				+ "/{theId}/updateMechanic: Update Mechanic Info \n "
				+ "/{theGarageId}/deleteMechanic/{theMechanicId}: Delete Mechanic Details \n"
				+ "/{theId}/orders: Get all orders";
	}
	
	@PostMapping("/{theId}/addMechanic")
	public String addMechanic(@RequestBody MechanicDTO theMechanicDTO, @PathVariable("theId") Long theId) {
		 
		Mechanic theMechanic = modelMapper.map(theMechanicDTO, Mechanic.class);
		return "Mechanic Added Successfully: Login Detail:\n " + garageService.addMechanic(theId, theMechanic);
	}
	
	@GetMapping("/{theId}/mechanicStatus")
	public String findMechanicStatus(@PathVariable("theId") Long theId) {
		return garageService.findMechanicStatus(theId);
	}
	
	@GetMapping("/findById/{theId}")
	public String getGarageOwner(@PathVariable Long theId) {
		
		return garageService.getGarageOwner(theId).toString();
	
	}

	@PutMapping("/{theId}/updateMechanic/")
	public String updateMechanic(@RequestBody MechanicDTO theMechanicDTO, @PathVariable Long theId){

		Mechanic theMechanic = modelMapper.map(theMechanicDTO, Mechanic.class);
		return garageService.updateMechanic(theId, theMechanic);
	}

	@DeleteMapping("/{theGarageOwnerId}/deleteMechanic/{theMechanicId}")
	public String deleteMechanic(@PathVariable Long theMechanicId){

		return garageService.deleteMechanic(theMechanicId);

	}
	
	@GetMapping("/{theId}/orders")
	public String findAllOrders(@PathVariable Long theId) {
		
		return garageService.findAllOrders(theId);
	}
}
