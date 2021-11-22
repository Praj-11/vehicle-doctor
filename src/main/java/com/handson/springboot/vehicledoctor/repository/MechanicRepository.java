package com.handson.springboot.vehicledoctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handson.springboot.vehicledoctor.enitity.Mechanic;

public interface MechanicRepository extends JpaRepository<Mechanic, Long>{

	List<Mechanic> findByEmail(String email);
}
