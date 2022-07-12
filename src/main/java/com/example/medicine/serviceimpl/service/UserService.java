package com.example.medicine.serviceimpl.service;

import com.example.medicine.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    User save(User users);
    void delete(Long id);
}