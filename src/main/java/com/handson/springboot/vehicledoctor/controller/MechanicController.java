package com.handson.springboot.vehicledoctor.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.springboot.vehicledoctor.enitity.SparePart;
import com.handson.springboot.vehicledoctor.service.MechanicService;


@RestController
@RequestMapping("/api/mechanic")
public class MechanicController {

	@Autowired
	private MechanicService mechanicService;
	
	@GetMapping("/list")
	public String listMechanic() {	
		return mechanicService.findAllMechanic();	
	}
	
	@PutMapping("/{theOrderTrackingNumber}/taskCompleted")
	public String taskCompleted(@RequestBody List<SparePart> spareParts, 
			@PathVariable("theOrderTrackingNumber") String theOrderTrackingNumber) {
	
		return mechanicService.taskCompleted(spareParts,theOrderTrackingNumber);
	}
	
	@GetMapping("{theMechanicId}/pendingOrders")
	public String pendingOrders(@PathVariable("theMechanicId") Long theMechanicId) {
		return mechanicService.findPendingOrders(theMechanicId).toString();
	}
}
