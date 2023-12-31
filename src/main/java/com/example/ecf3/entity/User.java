package com.example.ecf3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    private int currentRanking;
    private boolean admin;

    @OneToMany(mappedBy = "user1",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Game> games;


    @Override
    public String toString() {
        return " Prénom = " + firstName +
                ", Nom = " + lastName ;
    }
}
