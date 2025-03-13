package com.SpringBootCaching.SpringBootCaching.repository;

import com.SpringBootCaching.SpringBootCaching.entities.SalarlyEntities;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalarlyRepo extends CrudRepository<SalarlyEntities,Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SalarlyEntities> findById(Long id);
}
