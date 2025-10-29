package com.xp.xpposts.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="userDetails")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String name;

}
