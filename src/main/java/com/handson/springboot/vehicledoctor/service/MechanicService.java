package com.handson.springboot.vehicledoctor.service;

import java.util.List;
import java.util.Optional;

import com.handson.springboot.vehicledoctor.enitity.Mechanic;
import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.enitity.SparePart;

public interface MechanicService {
	
	public void addMechanic(Mechanic theMechanic);

	void deleteMechanic(Long theId);

	Optional<Mechanic> findById(Long theId);

	public boolean checkEmail(String emailString);

	public boolean existsById(Long theId, Mechanic theMechanic);

	public String findAllMechanic();

	public String taskCompleted(OrderTable orderTable, Long theOrderId);

	public List<OrderTable> findPendingOrders(Long theMechanicId);

	

	
}
