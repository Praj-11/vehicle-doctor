package com.handson.springboot.vehicledoctor.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.handson.springboot.vehicledoctor.enitity.Garage;
import com.handson.springboot.vehicledoctor.enitity.Mechanic;

public interface GarageRepository extends JpaRepository<Garage, Long> {

	List<Garage> findByEmail(String email);

	@Query("Select g from Garage g where g.address.city=:city")
	List<Garage> findByCity (@Param("city") String city);
}
