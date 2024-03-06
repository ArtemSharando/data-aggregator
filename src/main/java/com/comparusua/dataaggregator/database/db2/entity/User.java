package com.comparusua.dataaggregator.database.db2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ldap_id")
    private UUID id;
    @Column(name = "ldap_login")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
}
