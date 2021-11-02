package com.handson.springboot.vehicledoctor.service;

import java.util.List;
import java.util.Optional;

import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.enitity.SparePart;

public interface OrderService {

	String placeOrder(OrderTable order);

	String addSpareParts(List<SparePart> spareParts, String theOrderTrackingNumber);

	void saveOrder(OrderTable tempOrder);

}
