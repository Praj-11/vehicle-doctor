package com.handson.springboot.vehicledoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.springboot.vehicledoctor.enitity.OrderTable;
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
	
	@PutMapping("{theOrderId}/taskCompleted")
	public String taskCompleted(@RequestBody OrderTable orderTable, @PathVariable("theOrderId") Long theOrderId) {
		return mechanicService.taskCompleted(orderTable,theOrderId);
	}
	
	@GetMapping("{theMechanicId}/pendingOrders")
	public String pendingOrders(@PathVariable("theMechanicId") Long theMechanicId) {
		return mechanicService.findPendingOrders(theMechanicId).toString();
	}
}
