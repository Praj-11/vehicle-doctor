package com.handson.springboot.vehicledoctor.service;

import java.util.Optional;

import com.handson.springboot.vehicledoctor.enitity.Customer;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;

public interface MechanicService {
	
	public void addMechanic(Mechanic theMechanic);

	void deleteMechanic(Long theId);

	Optional<Mechanic> findById(Long theId);

	public boolean checkEmail(String emailString);

	public boolean existsById(Long theId, Mechanic theMechanic);

	public Mechanic login(String email, String password);
}
