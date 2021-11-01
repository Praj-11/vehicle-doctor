package com.handson.springboot.vehicledoctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.handson.springboot.vehicledoctor.enitity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByEmail(String email);


}
