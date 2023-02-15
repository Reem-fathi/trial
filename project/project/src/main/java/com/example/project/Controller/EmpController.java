package com.example.project.Controller;

import com.example.project.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.project.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class EmpController {
  @Autowired
  private EmployeeService authControllerService;
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/getEmployee")
  public ResponseEntity<List<Employee>> getAllEmployee(){
    return authControllerService.getAllEmployee();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/addEmployee")
  public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
    return authControllerService.addEmployee(employee);
  }
  @GetMapping("getEmployee/{empId}")
  public  ResponseEntity<Employee> getEmployee(@PathVariable Integer empId){

    return authControllerService.getEmployee(empId);
  }
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("updateEmployee/{empId}")
  public ResponseEntity<String> updateEmployee(@PathVariable Integer empId,@RequestBody Employee employee){
    return  authControllerService.updateEmployee(empId,employee);

  }
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("deleteEmployee/{empId}")
  public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId){
    return authControllerService.deleteEmployee(empId);
  }
@GetMapping("/getDetail/{email}")
  public ResponseEntity<Employee>getDetail(@PathVariable String email){
    return authControllerService.getDetail(email);

}

}

