package com.handson.springboot.vehicledoctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.handson.springboot.vehicledoctor.enitity.OrderTable;

public interface OrderRepository extends JpaRepository<OrderTable, Long> {

	OrderTable findByOrderTrackingNumber(String theOrderTrackingNumber);
	
	@Query("Select o from OrderTable o where o.mechanic.id=:theMechanicId AND o.status=:status")
	List<OrderTable> findPendingOrders(@Param("theMechanicId") Long theMechanicId,@Param("status") char status);
	
}
