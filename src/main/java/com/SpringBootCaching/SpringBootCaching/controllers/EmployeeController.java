package com.SpringBootCaching.SpringBootCaching.controllers;

import com.SpringBootCaching.SpringBootCaching.dto.EmployeeDto;
import com.SpringBootCaching.SpringBootCaching.entities.SalarlyEntities;
import com.SpringBootCaching.SpringBootCaching.services.EmployeeService;
import com.SpringBootCaching.SpringBootCaching.services.SalarlyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {


   private final EmployeeService employeeService;
   private final SalarlyService salarlyService;


   @GetMapping("/{id}")
   public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
      EmployeeDto employeeDto = employeeService.getEmployeeById(id);
      return ResponseEntity.ok(employeeDto);
   }

   @PostMapping
   public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
      EmployeeDto createdEmployeeDto = employeeService.createNewEmployee(employeeDto);
      return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
   }

   @PutMapping("/{id}")
   public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
      EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
      return ResponseEntity.ok(updatedEmployee);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
      employeeService.deleteEmployee(id);
      return ResponseEntity.noContent().build();
   }

   @PutMapping("/incrementBalance/{accountId}")
   public ResponseEntity<SalarlyEntities> incrementBalance(@PathVariable Long accountId) {
      SalarlyEntities salaryAccount = salarlyService.incrementBalance(accountId);
      return ResponseEntity.ok(salaryAccount);
   }


}
