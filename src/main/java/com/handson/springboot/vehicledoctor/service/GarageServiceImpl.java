package com.handson.springboot.vehicledoctor.service;

import java.time.LocalDate;
import java.util.Date;

import java.util.Optional;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;


import javax.servlet.http.HttpServletResponse;
import org.hibernate.engine.query.spi.ReturnMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.dao.GarageRepository;
import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;


import ch.qos.logback.core.filter.Filter;


@Service
public class GarageServiceImpl implements GarageService{

	@Autowired
	private GarageRepository garageRepository;
	
	@Autowired
	private MechanicService mechanicService;
	
	
	@Override
	public String addMechanic(Long theId, Mechanic theMechanic) {
		
		Optional<Garage> garageOwner = getGarageOwner(theId);
		
		String emailString = theMechanic.getName().toLowerCase() + "@" + garageOwner.get().getGarageName().split(" ")[0].toLowerCase() + ".com";
		
		while (!mechanicService.checkEmail(emailString)) {
			
			emailString = emailString.substring(0, theMechanic.getName().length()) + "1" + emailString.substring(theMechanic.getName().length());
		}
		
		String passwordString = theMechanic.getName() + "123";
		
		theMechanic.setEmail(emailString);
		theMechanic.setPassword(passwordString);
		
		garageOwner.ifPresent(theMechanic::setEmployer);
		
		theMechanic.setActive(true);
		
		mechanicService.addMechanic(theMechanic);
		
		return "Email: " + emailString + ", Password: " + passwordString;
	}

	@Override
	public Optional<Garage> getGarageOwner(Long id) {
		
		return garageRepository.findById(id);
	}

	@Override
	public String deleteMechanic(Long theId) {

		Optional<Mechanic> tempMechanic = mechanicService.findById(theId);

		if (tempMechanic.isEmpty()){

			return "Mechanic Not Found";
		}

		mechanicService.deleteMechanic(theId);

		return "Mechanic Deleted: " + tempMechanic;
	}

	@Override
	public Garage login(String email, String password) {
		// TODO Auto-generated method stub
		
		if (!garageRepository.findByEmail(email).isEmpty()) {
			
			
			Garage tempGarage = garageRepository.findByEmail(email).get(0);
			
			return (tempGarage.getPassword().equals(password)) ? tempGarage : null;
			
		}
		
		return null;
	}

	@Override
	public String updateMechanic(Long theId, Mechanic theMechanic) {
		
		
		Boolean mechanicStatus = mechanicService.existsById(theId, theMechanic);
		
		if (mechanicStatus) {
			
			theMechanic.setEmployer(garageRepository.findById(theId).get());
			
			mechanicService.addMechanic(theMechanic);
			return "Mechanic Updated Successfully";
		}
		
		return "Invalid Mechanic Id";
	}


	@Override
	public Optional<Garage> findByCity(String city) {
		
		System.out.println("Garage Details By City: " + garageRepository.findByCity(city).size());
		
		return Optional.of(garageRepository.findByCity(city).get(0));
		
	}

	@Override
	public Optional<Mechanic> findMechanicByAvailability(Long id, Date appointmentDate) {
		
		Garage tempGarage = getGarageOwner(id).get();
		
		Date tempAppointmentDate = appointmentDate;
		
		tempAppointmentDate.setHours(tempAppointmentDate.getHours()-3);
		
		System.out.println("Mechanic Available: " + tempGarage.getMechanics());
		
		Optional<Mechanic> tempMechanic = tempGarage.getMechanics().stream().filter((temp) -> temp.getOrders().isEmpty()).findFirst();
		
		if (tempMechanic.isEmpty()) {
			
			tempMechanic = tempGarage.getMechanics().stream()
					.filter(
							(temp) -> temp.getOrders().stream()
							.filter((temp1) -> temp1.getStatus().equals('p') || temp1.getStatus().equals('o'))
							.allMatch((temp2) -> temp2.getOrderAppointmentDate().before(tempAppointmentDate))
							).findFirst();
		}
		
		return tempMechanic;
	}
	
	


}
