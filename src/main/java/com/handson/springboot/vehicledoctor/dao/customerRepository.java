package com.handson.springboot.vehicledoctor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handson.springboot.vehicledoctor.enitity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
