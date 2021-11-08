package com.handson.springboot.vehicledoctor.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.repository.MechanicRepository;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.enitity.SparePart;
import com.handson.springboot.vehicledoctor.exceptions.ApiRequestException;

@Service
public class MechanicServiceImpl implements MechanicService{

	private static final Logger logger = Logger.getLogger(MechanicServiceImpl.class);
	
	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired 
	private OrderService orderService;
	
	@Autowired
	private SparePartService sparePartService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Long addMechanic(Mechanic theMechanic) {
		
		return mechanicRepository.save(theMechanic).getId();
	}
	
	@Override
	public Mechanic login(String email, String password) {
		
		if (!mechanicRepository.findByEmail(email).isEmpty()) {		
			Mechanic tempMechanic = mechanicRepository.findByEmail(email).get(0);
			logger.info("Mechanic Details: " + tempMechanic);
	
			if (passwordEncoder.matches(password, tempMechanic.getPassword())) {				
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
