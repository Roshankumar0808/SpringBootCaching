package com.SpringBootCaching.SpringBootCaching.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SalarlyEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private BigDecimal balance;

//    @Version
//    private Long version;

   @OneToOne(fetch = FetchType.LAZY)
   @JsonIgnore
    private EmployeeEnitities employeeEnitities;
}
