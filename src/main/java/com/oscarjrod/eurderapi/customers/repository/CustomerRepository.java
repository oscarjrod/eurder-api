package com.oscarjrod.eurderapi.customers.repository;

import com.oscarjrod.eurderapi.customers.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
