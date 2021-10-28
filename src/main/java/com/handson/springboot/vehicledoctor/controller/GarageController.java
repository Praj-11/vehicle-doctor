package com.handson.springboot.vehicledoctor.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.service.GarageService;
import com.handson.springboot.vehicledoctor.service.MechanicService;

@RestController
@RequestMapping("/api/garage")
public class GarageController {
	
	@Autowired
	private GarageService garageService;
	
	@Autowired
	private MechanicService mechanicService;
	@GetMapping("/")
	public String getHello() {
		
		String tempString = "Current Supported Endpoints: \n\n /findById/{theId}: Shows garage details \n /{theId}/addMechanic: Add Mechanic \n /{theId}/updateMechanic: Update Mechanic Info \n /{theGarageId}/deleteMechanic/{theMechanicId}: Delete Mechanic Details";
		return tempString;
	}
	
	@PostMapping("/{theId}/addMechanic")
	public String addMechanic(@RequestBody Mechanic theMechanic, @PathVariable("theId") Long theId) {
		 
		return "Mechanic Added Successfully: Login Detail: " + garageService.addMechanic(theId, theMechanic);
	}
	
	@GetMapping("/{theId}/mechanicStatus")
	public String findMechanicStatus(@PathVariable("theId") Long theId) {
		return garageService.findMechanicStatus(theId).toString();
	}
	
	@GetMapping("/findById/{theId}")
	public String getGarageOwner(@PathVariable Long theId) {
		
		return garageService.getGarageOwner(theId).toString();
	
	}

	@PutMapping("/{theId}/updateMechanic/")
	public String updateMechanic(@RequestBody Mechanic theMechanic, @PathVariable Long theId){

		return garageService.updateMechanic(theId, theMechanic);
	}

	@DeleteMapping("/{theGarageOwnerId}/deleteMechanic/{theMechanicId}")
	public String deleteMechanic(@PathVariable Long theMechanicId){

		return garageService.deleteMechanic(theMechanicId);

	}
}
