package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  // Find all users sorted in ascending order
  List<User> findAllByOrderByIdAsc();

  // Find user by id
  Optional<User> findById(Long id);
}
