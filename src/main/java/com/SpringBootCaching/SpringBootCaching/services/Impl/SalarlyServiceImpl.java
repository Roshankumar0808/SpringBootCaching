package com.SpringBootCaching.SpringBootCaching.services.Impl;

import com.SpringBootCaching.SpringBootCaching.entities.EmployeeEnitities;
import com.SpringBootCaching.SpringBootCaching.entities.SalarlyEntities;
import com.SpringBootCaching.SpringBootCaching.repository.SalarlyRepo;
import com.SpringBootCaching.SpringBootCaching.services.SalarlyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class SalarlyServiceImpl implements SalarlyService {
     private final SalarlyRepo salarlyRepo;
    @Override
    public void createAccount(EmployeeEnitities employeeEnitities) {
        if(employeeEnitities.getName().equals("Roshan")){
            throw new RuntimeException("Roshan account cannot be created");
        }
        SalarlyEntities salarlyEntities= SalarlyEntities.builder()
                .employeeEnitities(employeeEnitities)
                .balance(BigDecimal.ZERO).build();

        salarlyRepo.save(salarlyEntities);
    }

    @Override
  //  @Transactional(isolation = Isolation.SERIALIZABLE)
    public SalarlyEntities incrementBalance(Long accountId) {

        SalarlyEntities salaryAccount = salarlyRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        BigDecimal prevBalance = salaryAccount.getBalance();
        BigDecimal newBalance = prevBalance.add(BigDecimal.valueOf(1L));

        salaryAccount.setBalance(newBalance);

        return salarlyRepo.save(salaryAccount);
    }
}
