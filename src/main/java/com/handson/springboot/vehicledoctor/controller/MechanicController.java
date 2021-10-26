package com.handson.springboot.vehicledoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.springboot.vehicledoctor.dao.MechanicRepository;

@RestController
@RequestMapping("/api/mechanic")
public class MechanicController {

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@GetMapping("/list")
	public void listMechanic() {
		
		System.out.println(mechanicRepository.findAll());
		return;
	}
}
