package com.SpringBootCaching.SpringBootCaching.services;

import com.SpringBootCaching.SpringBootCaching.entities.EmployeeEnitities;
import com.SpringBootCaching.SpringBootCaching.entities.SalarlyEntities;

public interface SalarlyService {
    void createAccount(EmployeeEnitities employeeEnitities);
    SalarlyEntities incrementBalance(Long accountId);
}
