package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findAllByOrderByIdAsc();

  Optional<User> findById(Long id);
}
