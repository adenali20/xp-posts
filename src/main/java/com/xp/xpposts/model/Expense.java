package com.xp.xpposts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    Integer amount;
    String description;
}
