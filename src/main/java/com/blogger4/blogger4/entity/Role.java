package com.blogger4.blogger4.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "roles")
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users=new HashSet<>();     //duplicat elements are not allowed in set so that we can use set  in user

    // Getters and setters

    // Constructors
}

