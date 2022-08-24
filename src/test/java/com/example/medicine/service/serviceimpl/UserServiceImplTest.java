package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Role;
import com.example.medicine.model.User;
import com.example.medicine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserRepository repository;

    private UserServiceImpl service;

    private User user;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(repository);
        user = new User(
                id,
                "user",
                "password",
                Role.ADMIN
        );
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<User>> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.getBody().size(), 0);
    }

    @Test
    void findById() {
        // given
        ResponseEntity<User> resultUser;
        User userSave;

        // when
        userSave = repository.save(user);
        resultUser = service.findById(userSave.getId());

        // then
        assertEquals(resultUser.getBody(), userSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<User> resultUser;
        user.setId(34L);

        // when
        resultUser = service.save(user);

        // then
        assertEquals(resultUser.getBody(), user);
    }

    @Test
    void delete() {
        // given
        User userSave;

        // when
        userSave = repository.save(user);
        service.delete(userSave.getId());

        // then
        assertThat(repository.findById(id)).isEqualTo(Optional.empty());
    }

    @Test
    void loadUserByUsernameWithoutException() {
        // given
        UserDetails userDetails;
        user.setId(null);

        // when
        service.save(user);
        userDetails = service.loadUserByUsername("user");

        // then
        assertThat(userDetails).isEqualTo(user);
    }

    @Test
    void loadUserByUsernameWithException() {
        // given
        UserDetails userDetails;

        // when
        userDetails = service.loadUserByUsername("u");

        // then
        assertThat(userDetails).isEqualTo(null);
    }
}