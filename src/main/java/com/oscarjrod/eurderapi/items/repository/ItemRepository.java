package com.oscarjrod.eurderapi.items.repository;

import com.oscarjrod.eurderapi.items.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
