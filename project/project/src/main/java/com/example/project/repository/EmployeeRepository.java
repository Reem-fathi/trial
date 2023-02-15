package com.example.project.repository;

import com.example.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
  public List<Employee> findByempId(Integer empId);


  Optional<Employee> findByEmail(String email);
}

