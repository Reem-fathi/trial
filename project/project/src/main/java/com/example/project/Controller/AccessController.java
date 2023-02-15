package com.example.project.Controller;

import com.example.project.dto.AccessRequest;
import com.example.project.dto.AccessResponse;
import com.example.project.dto.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.service.EntryService;

import java.lang.reflect.InvocationTargetException;


@RestController
@RequestMapping("/controller")
@CrossOrigin
public class AccessController {
  final Logger logger = LoggerFactory.getLogger(AccessController.class);
  @Autowired
  private EntryService entryService;

  @PostMapping("register")
  public ResponseEntity<AccessResponse> registerUser(@RequestBody AccessRequest request)throws IllegalAccessException, InvocationTargetException {
    logger.info("Received a request to the endpoint '/register' : {}",request);
    return entryService.registerUser(request);
  }
  @PostMapping("login")
  public ResponseEntity<JwtResponse> loginUser(@RequestBody AccessRequest request){
    logger.info("Received a request to the endpoint '/login' : email : {}, password :{}",request.getEmail(),request.getPassword());
    return entryService.loginUser(request);
  }
}


