package com.handson.springboot.vehicledoctor.service;


import java.util.Date;
import java.util.Optional;

import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;

public interface GarageService {
	
	public String addMechanic(Long theId, Mechanic theMechanic);
	
	public Optional<Garage> getGarageOwner(Long id);

	String deleteMechanic(Long theMechanicId);

	public Garage login(String email, String password);

	public String updateMechanic(Long theId, Mechanic theMechanic);


	public Optional<Garage> findByCity(String city);

	public Optional<Mechanic> findMechanicByAvailability(Long id, Date appointmentDate);

}
