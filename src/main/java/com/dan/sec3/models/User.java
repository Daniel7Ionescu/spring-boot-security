package com.dan.sec3.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first)name")
    public String firstName;

    @Column(name = "email", unique = true)
    public String email;

    @Column(name = "password")
    public String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;

    public User() {
        this.authorities = new HashSet<>();
    }

    public User(String firstName, String email, String password, Set<Role> authorities) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
}
