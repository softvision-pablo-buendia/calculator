package com.pablo.calculator.domain;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private UUID id;
    private String name;
    private List<String> results;
}
