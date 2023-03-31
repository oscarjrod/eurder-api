package com.oscarjrod.eurderapi.orders.repository;

import com.oscarjrod.eurderapi.orders.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
