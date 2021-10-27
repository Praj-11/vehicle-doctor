package com.handson.springboot.vehicledoctor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.apache.el.parser.AstFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.dao.MechanicRepository;
import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;

@Service
public class MechanicServiceImpl implements MechanicService{

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired 
	private OrderRepository orderRepository;
	
	@Override
	public Mechanic login(String email, String password) {
		
		if (!mechanicRepository.findByEmail(email).isEmpty()) {
			
			
			Mechanic tempMechanic = mechanicRepository.findByEmail(email).get(0);
			
			return (tempMechanic.getPassword().equals(password)) ? tempMechanic : null;
			
		}
		
		return null;
	}
	
	@Override
	public void addMechanic(Mechanic theMechanic) {
		
		mechanicRepository.save(theMechanic);
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
		// TODO Auto-generated method stub
		return mechanicRepository.findByEmail(emailString).isEmpty();
	}

	@Override
	public boolean existsById(Long theId, Mechanic theMechanic) {
		// TODO Auto-generated method stub
		
		Optional<Mechanic> tempMechanic = mechanicRepository.findById(theMechanic.getId());
		
		Long tempId = tempMechanic.get().getEmployer().getId();
		
		if(tempId == theId) {
			
			theMechanic.getAddress().setId(tempMechanic.get().getAddress().getId());
			return true;
		}
		
		return false;
		
	}

	@Override
	public List<Order> viewOrders(Long theId) {
		List<Order> li= orderRepository.findAll();
		List<Order> tempList= new ArrayList<Order>();
		for(Order order :li) {
			if(order.getMechanicId()==theId) {
				tempList.add(order);
			}
		}
		
		return tempList;
	}


}
