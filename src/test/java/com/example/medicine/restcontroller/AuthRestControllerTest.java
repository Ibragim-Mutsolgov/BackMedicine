package com.example.medicine.restcontroller;

import com.example.medicine.model.Role;
import com.example.medicine.model.User;
import com.example.medicine.repository.UserRepository;
import com.example.medicine.security.JwtResponse;
import com.example.medicine.security.JwtUtil;
import com.example.medicine.service.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class AuthRestControllerTest {

    private AuthRestController controller;

    private JwtUtil jwtUtil;

    private UserServiceImpl service;

    @Mock
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                "user",
                "pass",
                Role.ADMIN
        );
        jwtUtil = new JwtUtil();
        service = new UserServiceImpl(repository);
        controller = new AuthRestController(jwtUtil, service, manager);
        service.save(user);
    }

    @Test
    void createToken() throws Exception {
        // given
        ResponseEntity<?> response;
        JwtResponse jwtResponse;

        // when
        response = controller.createToken(user);
        jwtResponse = (JwtResponse) response.getBody();

        // then
        assertTrue(jwtUtil.validateToken(jwtResponse.getToken()));
    }

    @Test
    void badCredentials() throws Exception {
        // given
        ResponseEntity<?> response;
        JwtResponse jwtResponse;

        // when
        Throwable throwable = assertThrows(Exception.class, () -> {
            controller.createToken(new User("u", "p", Role.ADMIN));
        });

        // then
        assertNotNull(throwable.getMessage());
    }
}