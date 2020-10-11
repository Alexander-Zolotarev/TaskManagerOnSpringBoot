package com.project.repository;

import com.project.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByFirstName(String firstName);
    Optional<User> findById(Integer id);
    User findByFirstNameAndLastName(String firstName, String lastName);
}
