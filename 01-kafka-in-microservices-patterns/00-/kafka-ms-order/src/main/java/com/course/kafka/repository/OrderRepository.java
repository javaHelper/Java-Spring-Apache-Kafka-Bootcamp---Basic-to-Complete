package com.course.kafka.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.course.kafka.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
