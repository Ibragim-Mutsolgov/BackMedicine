package com.example.medicine.service;

import com.example.medicine.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User users);

    void delete(Long id);
}