package com.ck.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ck.restservices.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

}
