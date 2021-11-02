package com.handson.springboot.vehicledoctor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.springboot.vehicledoctor.enitity.SparePart;
import com.handson.springboot.vehicledoctor.repository.SparePartRepository;

import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

@Service
public class SparePartServiceImpl implements SparePartService {

	@Autowired
	private SparePartRepository sparePartRepository;
	
	@Override
	public List<SparePart> getSpareParts(List<SparePart> spareParts) {
		
		List<SparePart> tempSpareParts = new ArrayList<>();
		
		spareParts.stream().forEach(temp-> {
			
			List<SparePart> temp2 = sparePartRepository.findByPartName(temp.getPartName());
			
			if (temp2 != null && !temp2.isEmpty()) {
				
				tempSpareParts.add(temp2.get(0));
			}else {
				
				System.out.println("Part doesn't exists. Please check part name: " + temp.getPartName());
			}
		});
		
		
		
		return tempSpareParts;
	}

}
