package com.example.medicine.service;

import com.example.medicine.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> findAll();

    ResponseEntity<User> findById(Long id);

    ResponseEntity<User> save(User users);

    ResponseEntity<User> delete(Long id);
}