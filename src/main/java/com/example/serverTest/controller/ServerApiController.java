package com.example.serverTest.controller;

import com.example.serverTest.dto.Req;
import com.example.serverTest.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
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
  public Req<User> post(
                        @RequestBody Req<User> user,
                        @PathVariable String userName,
                        @PathVariable String userAge,
                        @RequestHeader("x-authorization") String authorization,
                        @RequestHeader("custom-header") String header){
    log.info("client req : {}", user);
    log.info("Info : {}, {}", userName, userAge);
    log.info("Info : {}, {}", authorization, header);

    Req<User> response = new Req<>();
    response.setHeader(
        new Req.Header()
    );
    response.setBody(
        user.getBody()
    );
    return response;
  }
}
