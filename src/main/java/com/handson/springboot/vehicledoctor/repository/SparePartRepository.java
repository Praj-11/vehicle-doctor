package com.handson.springboot.vehicledoctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handson.springboot.vehicledoctor.enitity.SparePart;

public interface SparePartRepository extends JpaRepository<SparePart, Long>{

	List<SparePart> findByPartName(String partName);

}
