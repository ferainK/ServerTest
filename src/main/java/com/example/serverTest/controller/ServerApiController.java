package com.example.serverTest.controller;

import com.example.serverTest.dto.Req;
import com.example.serverTest.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

  //https://openapi.naver.com/v1/search/local.xml
  // ?query=고기집
  // &display=10
  // &start=1
  // &sort=random
  @GetMapping("/naver")
  public String naver(){
    String query = "중국집";

    URI uri = UriComponentsBuilder
        .fromUriString("https://openapi.naver.com")
        .path("v1/search/local.json")
        .queryParam("query", query)
        .queryParam("display",10)
        .queryParam("start", 1)
        .queryParam("sort", "random")
        .encode(Charset.forName("UTF-8"))
        .build().toUri();

    RestTemplate restTemplate = new RestTemplate();
    RequestEntity<Void> req = RequestEntity
        .get(uri)
        .header("X-Naver-Client-Id","xIIUtiIxl8QCwV_I7xMz")
        .header("X-Naver-Client-Secret", "X8mLnvTeUO")
        .build();

    ResponseEntity<String> result = restTemplate.exchange(req, String.class);

    return result.getBody();
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
