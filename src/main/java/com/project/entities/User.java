package com.project.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @Size(min = 2, max = 30, message = "First name should be from 2 to 30 symbols")
    private String firstName;

    @Column(name= "last_name")
    @Size(min = 2, max = 30, message = "Last name should be from 2 to 30 symbols")
    private String lastName;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName=lastName;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}