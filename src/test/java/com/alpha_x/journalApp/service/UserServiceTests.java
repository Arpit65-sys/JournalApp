package com.alpha_x.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alpha_x.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

  @Autowired
  private UserRepository userRepository;
  
  @ParameterizedTest
  @ValueSource(strings = {
    "RAM",
    "kk23-1",
    "Arpit"
  })
  public void testFindByUserName(String name){
    assertNotNull(userRepository.findByUserName(name));
  }

  @Disabled
  @ParameterizedTest
  @CsvSource({
    "1,2,3",
    "2,2,4",
    "2,34,5"
  })
  public void addition(int a, int b, int expected){
    assertEquals(expected, a+b);
  }

}
