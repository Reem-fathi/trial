package com.example.project.dto;


import com.example.project.service.EntryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccessRequest {
  final Logger logger = LoggerFactory.getLogger(EntryService.class);
  private String name;
  private String email;
  private String password;
  private Integer phone;

  public AccessRequest(String email, String password){
    this.email = email;
    this.password=password;
  }
}
