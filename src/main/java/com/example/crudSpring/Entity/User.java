package com.example.crudSpring.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(length = 50)
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    @Column(name = "mail", nullable = false, length = 50, unique = true)
    private String email;
    @Getter
    @Setter
    private boolean enabled;
}
