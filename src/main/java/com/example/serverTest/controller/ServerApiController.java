package com.example.serverTest.controller;

import com.example.serverTest.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerApiController {
  @GetMapping("")
  public User hello(@RequestParam String name, @RequestParam int age){
    User user = new User();
    user.setName(name);
    user.setAge(age);
    return user;
  }


  @PostMapping("/{userName}/{userAge}")
  public User post(@RequestBody User user, @PathVariable String userName, @PathVariable String userAge){
    log.info("client req : {}", user);
    log.info("Info : {}, {}", userName, userAge);
    return user;

  }
}
