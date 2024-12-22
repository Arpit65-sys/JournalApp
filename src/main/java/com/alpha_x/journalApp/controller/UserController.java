package com.alpha_x.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.alpha_x.journalApp.entity.User;
import com.alpha_x.journalApp.repository.UserRepository;
import com.alpha_x.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (userName == null || userName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied.");
        }
        User userInDb = userService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      userRepository.deleteByUserName(authentication.getName());
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
