package com.SpringBootCaching.SpringBootCaching.services;

import com.SpringBootCaching.SpringBootCaching.dto.EmployeeDto;

public interface EmployeeService {
    public EmployeeDto getEmployeeById(Long Id);
    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
}
