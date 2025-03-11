package com.SpringBootCaching.SpringBootCaching.services.Impl;

import com.SpringBootCaching.SpringBootCaching.dto.EmployeeDto;
import com.SpringBootCaching.SpringBootCaching.entities.EmployeeEnitities;
import com.SpringBootCaching.SpringBootCaching.exception.ResourceNotFoundException;
import com.SpringBootCaching.SpringBootCaching.repository.EmployeeRepo;
import com.SpringBootCaching.SpringBootCaching.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;
    private final String CACHE_NAME="employees";

    @Override
    @Cacheable(cacheNames = CACHE_NAME,key = "#Id")
    public EmployeeDto getEmployeeById(Long Id) {
        EmployeeEnitities employeeEnitities=employeeRepo.findById(Id).orElseThrow(()-> {
            log.info("Employee Not Found With id:" , Id);
            return new ResourceNotFoundException("Employee Not Found With id:" + Id);
        });
        EmployeeDto employeeDto=modelMapper.map(employeeEnitities, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME,key = "#result.id")
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        List<EmployeeEnitities> employeeEnitities=employeeRepo.findByEmail(employeeDto.getEmail());

        if(!employeeEnitities.isEmpty()){
            log.error("Employee Not Found with email:"+employeeDto.getEmail());
            throw  new ResourceNotFoundException("Employee Not Found with email:"+employeeDto.getEmail());

        }
        EmployeeEnitities employeeEnity=modelMapper.map(employeeDto, EmployeeEnitities.class);
        EmployeeEnitities saveemployee=employeeRepo.save(employeeEnity);
        return modelMapper.map(saveemployee, EmployeeDto.class);
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME,key = "#id")
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        log.info("Updating employee with id: {}", id);
        EmployeeEnitities employee = employeeRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new ResourceNotFoundException("Employee not found with id: " + id);
                });

        if (!employee.getEmail().equals(employeeDto.getEmail())) {
            log.error("Attempted to update email for employee with id: {}", id);
            throw new RuntimeException("The email of the employee cannot be updated");
        }

        modelMapper.map(employeeDto, employee);
        employee.setId(id);

        EmployeeEnitities savedEmployee = employeeRepo.save(employee);
        log.info("Successfully updated employee with id: {}", id);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME,key = "#id")
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        boolean exists = employeeRepo.existsById(id);
        if (!exists) {
            log.error("Employee not found with id: {}", id);
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }

        employeeRepo.deleteById(id);
        log.info("Successfully deleted employee with id: {}", id);
    }
}
