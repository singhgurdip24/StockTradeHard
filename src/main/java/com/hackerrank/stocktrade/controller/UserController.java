package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.SaveUserRequest;
import com.hackerrank.stocktrade.payload.UserResponse;
import com.hackerrank.stocktrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<UserResponse> getAllUsers(){
    return userService.getAllUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<?> postNewUser(@RequestBody SaveUserRequest saveNewUser){
    User user = userService.saveNewUser(saveNewUser);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
