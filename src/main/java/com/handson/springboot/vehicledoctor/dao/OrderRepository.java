package com.handson.springboot.vehicledoctor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handson.springboot.vehicledoctor.enitity.OrderTable;

public interface OrderRepository extends JpaRepository<OrderTable, Long> {

}
