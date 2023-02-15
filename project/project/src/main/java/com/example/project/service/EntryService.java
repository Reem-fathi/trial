package com.example.project.service;

import com.example.project.dto.AccessRequest;
import com.example.project.dto.AccessResponse;
import com.example.project.dto.JwtResponse;
import com.example.project.exception.EmailAlreadyExistsException;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.Role;
import com.example.project.model.User;
import com.example.project.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.repository.UserRepository;

@Service
public class EntryService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CustomUserDetailService customUserDetailService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private RoleRepository repository;
  @Autowired
  private JwtService jwtService;

  @Autowired
  private PasswordEncoder passwordEncoder;
  final Logger logger= LoggerFactory.getLogger(EntryService.class);
  public ResponseEntity<AccessResponse>registerUser(AccessRequest request){
    if(Boolean.TRUE.equals(userRepository.existsByemail(request.getEmail()))){
      logger.error("user already exist");
      throw new EmailAlreadyExistsException("Email already exists");
    }
    User user = new User();
    Role role = repository.findByName("ROLE_USER").orElseThrow(()->new UsernameNotFoundException("ROLE_USER EXCEPTION"));
    user.setRole(role);
    user.setName(request.getName());
    user.setPhone(request.getPhone());

    user.setEmail(request.getEmail());

    user.setPassword(passwordEncoder.encode(request.getPassword()));
    logger.info("user registered");
    userRepository.save(user);
    return ResponseEntity.ok(new AccessResponse(user.getEmail()+" successfully registered",true));
  }

  public ResponseEntity<JwtResponse>loginUser(AccessRequest request){
    try{
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      logger.info("user logged in");
      Role role=userRepository.findByemail(request.getEmail()).get().getRole();
      return ResponseEntity.ok(new JwtResponse(jwtService.generateToken(request.getEmail()),true,role.getName())) ;
    }
    catch (AuthenticationException ex){
      logger.error("invalid user credentials");
      throw new ResourceNotFoundException("User",request.getEmail(),"invalid user email or password");
    }
  }
}




