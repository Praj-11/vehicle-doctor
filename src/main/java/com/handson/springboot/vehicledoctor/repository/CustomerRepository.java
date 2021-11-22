package com.handson.springboot.vehicledoctor.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository; 
import com.handson.springboot.vehicledoctor.enitity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByEmail(String email);


}
