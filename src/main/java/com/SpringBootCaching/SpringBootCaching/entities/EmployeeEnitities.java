package com.SpringBootCaching.SpringBootCaching.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class EmployeeEnitities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private Long salary;
}
