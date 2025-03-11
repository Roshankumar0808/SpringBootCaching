package com.SpringBootCaching.SpringBootCaching.repository;

import com.SpringBootCaching.SpringBootCaching.entities.EmployeeEnitities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEnitities,Long> {
   List<EmployeeEnitities> findByEmail(String Email);
}
