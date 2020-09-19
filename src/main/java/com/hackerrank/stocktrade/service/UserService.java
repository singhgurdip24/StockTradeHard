package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.SaveUserRequest;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User saveNewUser(SaveUserRequest saveNewUser) {

    User user = new User();
    user.setId(saveNewUser.getId());
    user.setName(saveNewUser.getName());

    userRepository.save(user);

    return user;
  }
}
