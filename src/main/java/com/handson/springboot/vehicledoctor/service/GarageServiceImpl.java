package com.handson.springboot.vehicledoctor.service;

import java.security.InvalidAlgorithmParameterException;
import java.time.LocalDate;
import java.util.Date;

import java.util.Optional;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import org.hibernate.engine.query.spi.ReturnMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.exceptions.ApiRequestException;
import com.handson.springboot.vehicledoctor.repository.GarageRepository;

import ch.qos.logback.core.filter.Filter;


@Service
public class GarageServiceImpl implements GarageService{

	@Autowired
	private GarageRepository garageRepository;
	
	@Autowired
	private MechanicService mechanicService;
	

	private static final String invalidGarageIdError = "Invalid Garage Id";
	
	
	@Override
	public String addMechanic(Long theId, Mechanic theMechanic) {
		
		Optional<Garage> garageOwner = getGarageOwner(theId);
		
		if (garageOwner.isEmpty()) {
			
			throw new ApiRequestException(invalidGarageIdError);
		}
		
		String emailString = theMechanic.getName().toLowerCase() + "@" + garageOwner.get().getGarageName().split(" ")[0].toLowerCase() + ".com";
		
		while (!mechanicService.checkEmail(emailString)) {
			
			emailString = emailString.substring(0, theMechanic.getName().length()) + "1" + emailString.substring(theMechanic.getName().length());
		}
		
		String passwordString = theMechanic.getName() + "123";
		
		theMechanic.setEmail(emailString);
		theMechanic.setPassword(passwordString);
		
		garageOwner.ifPresent(theMechanic::setEmployer);
		
		theMechanic.setActive(true);
		
		Long mechanicId =  mechanicService.addMechanic(theMechanic);
		
		return "Id: " + mechanicId + "Email: " + emailString + ", Password: " + passwordString;
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
		
		if (!garageRepository.findByEmail(email).isEmpty()) {
			
			
			Garage tempGarage = garageRepository.findByEmail(email).get(0);
			
			return (tempGarage.getPassword().equals(password)) ? tempGarage : null;
			
		}
		
		return null;
	}

	@Override
	public String updateMechanic(Long theId, Mechanic theMechanic) {
		
		
		Boolean mechanicStatus = mechanicService.existsById(theId, theMechanic);
		
		if (Boolean.TRUE.equals(mechanicStatus)) {
			
			Optional<Garage> tempGarage = garageRepository.findById(theId);
			
			if (tempGarage.isEmpty()) {
				
				throw new ApiRequestException(invalidGarageIdError);
			}
			
			theMechanic.setEmployer(tempGarage.get());
			
			mechanicService.addMechanic(theMechanic);
			
			return "Mechanic Updated Successfully";
		}
		
		return "Invalid Mechanic Id";
	}


	@Override
	public Optional<Garage> findByCity(String city) {
		
		List<Garage> temp = garageRepository.findByCity(city);
		
		return (!temp.isEmpty()) ? Optional.of(temp.get(0)) : Optional.empty();
		
	}

	@Override
	public Optional<Mechanic> findMechanicByAvailability(Long id, Date appointmentDate) {
		
		Optional<Garage> garage = getGarageOwner(id);
		
		if (garage.isEmpty()) {

			throw new ApiRequestException(invalidGarageIdError);
		}
		
		Garage tempGarage = garage.get();
		
		Date tempAppointmentDate = appointmentDate;
		
		tempAppointmentDate.setHours(tempAppointmentDate.getHours()-3);
		
		System.out.println("Mechanic Available: " + tempGarage.getMechanics());
		
		Optional<Mechanic> tempMechanic = tempGarage.getMechanics().stream().filter(temp -> {
			
			if (temp.getOrders().isEmpty()) {
				
				return true;
			}
			List<OrderTable> tempOrder = temp.getOrders().stream().filter(temp1 -> temp1.getStatus().equals('c')).collect(Collectors.toList());
				
			return tempOrder.size() == temp.getOrders().size();
				
		}).findFirst();
		
		if (tempMechanic.isEmpty()) {
			
			tempMechanic = tempGarage.getMechanics().stream()
					.filter(
							temp -> temp.getOrders().stream()
							.filter(temp1 -> temp1.getStatus().equals('p') || temp1.getStatus().equals('o'))
							.allMatch(temp2 -> temp2.getOrderAppointmentDate().before(tempAppointmentDate))
							).findFirst();
		}
		
		return tempMechanic;
	}

	@Override
	public String findMechanicStatus(Long theId) {
		Optional<Mechanic> tempMechanic = mechanicService.findById(theId);
		
		
		if(!tempMechanic.isPresent()) {
			return "Mechanic is not found, Please provide valid id !!!";
		} else if(tempMechanic.get().getOrders().isEmpty()) {
			return "Mechanic Status: \n" + tempMechanic.get().getName()+ " is Available.";
		}
		return tempMechanic.get().getOrders().get(0).getStatus().toString().equalsIgnoreCase("c") ? "Mechanic Status: \n" + tempMechanic.get().getName()+ " is Available.":"Mechanic Status: \n" + tempMechanic.get().getName()+ " is busy in repairing a vehicle of " +tempMechanic.get().getOrders().get(0).getCustomer().getName() +". His appointment date/time is : "+tempMechanic.get().getOrders().get(0).getOrderAppointmentDate();
	}

	@Override
	public String findAllOrders(Long theId) {
		
		if (!garageRepository.existsById(theId)) {
			
			return "Invalid Id";
		}
		
		Optional<Garage> tempGarage = garageRepository.findById(theId);
		
		if (tempGarage.isEmpty()) {

			throw new ApiRequestException(invalidGarageIdError);
		}
		
		return tempGarage.get().getOrders().toString();
	}

}
