package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
