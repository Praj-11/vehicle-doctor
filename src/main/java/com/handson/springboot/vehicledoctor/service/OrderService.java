package com.handson.springboot.vehicledoctor.service;

import java.util.List;

import com.handson.springboot.vehicledoctor.enitity.OrderTable;
import com.handson.springboot.vehicledoctor.enitity.SparePart;

public interface OrderService {

	String placeOrder(OrderTable order);

	String addSpareParts(List<SparePart> spareParts, String theOrderTrackingNumber);

}
