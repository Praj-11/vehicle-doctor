package com.handson.springboot.vehicledoctor.service;

import java.util.List;

import com.handson.springboot.vehicledoctor.enitity.SparePart;

public interface SparePartService {

	List<SparePart> getSpareParts(List<SparePart> spareParts);
	
}
