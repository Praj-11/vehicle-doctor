package com.handson.springboot.vehicledoctor.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.el.parser.AstFalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.dao.MechanicRepository;
import com.handson.springboot.vehicledoctor.dao.OrderRepository;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;

@Service
public class MechanicServiceImpl implements MechanicService{

	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired 
	private OrderRepository orderRepository;
	
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
	public String findAllMechanic() {
		// TODO Auto-generated method stub			
			return mechanicRepository.findAll().toString();
	}


	@Override
	public String taskCompleted(OrderTable orderTable, Long theOrderId) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Boolean orderStatus = mechanicRepository.existsById(orderTable.getId());
		System.out.println(orderTable.getId());
		System.out.println(orderStatus);
		if(orderStatus) {
			orderTable.setOrderCompleted(date);
			orderTable.setStatus('c');
			orderRepository.save(orderTable);
			return "Task has been completed by Mechanic !!!";
		}
		return "Invalid Order Id ";
	}

	@Override
	public List<OrderTable> findPendingOrders(Long theMechanicId) {
		// TODO Auto-generated method stub
		char status ='p';
		return orderRepository.findPendingOrders(theMechanicId,status);
	}

	

	


}
