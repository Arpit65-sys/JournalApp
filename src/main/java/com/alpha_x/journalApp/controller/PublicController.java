package com.alpha_x.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha_x.journalApp.entity.User;
import com.alpha_x.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

  @Autowired
  private UserService userService;

  @GetMapping("/health-check")
  public String healthCheck(){ return "OK-successfully"; }

  @PostMapping("/create-user")
  public String createUser(@RequestBody User user){
    userService.saveNewUser(user);
    return "CREATED";
  }

}