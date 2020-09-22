package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.SaveUserRequest;
import com.hackerrank.stocktrade.payload.UserResponse;
import com.hackerrank.stocktrade.repository.UserRepository;
import com.hackerrank.stocktrade.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

  public List<UserResponse> getAllUsers() {

    List<User> users = userRepository.findAllByOrderByIdAsc();

    return users.stream().map((user -> {
      return ModelMapper.mapUserToUserResponse(user);
    })).collect(Collectors.toList());
  }
}
