package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Role;
import com.example.medicine.domain.User;
import com.example.medicine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserRepository repository;

    private UserServiceImpl service;

    @Mock
    private JmsTemplate jmsTemplate;

    private User user;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(repository, jmsTemplate);
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
        List<User> list;

        // when
        list = service.findAll();
        verify(jmsTemplate).convertAndSend("userFindAll", true);

        // then
        assertEquals(list.size(), 0);
    }

    @Test
    void findById() {
        // given
        User userSave;

        // when
        userSave = repository.save(user);
        user = service.findById(userSave.getId());
        verify(jmsTemplate).convertAndSend("userFindById", user);

        // then
        assertEquals(user, userSave);
    }

    @Test
    void save() {
        // given
        User userSave;

        // when
        userSave = service.save(user);
        verify(jmsTemplate).convertAndSend("userSave", userSave);

        // then
        assertEquals(userSave, user);
    }

    @Test
    void delete() {
        // given

        // when
        repository.save(user);
        service.delete(id);
        verify(jmsTemplate).convertAndSend("userDelete", user);

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
        verify(jmsTemplate).convertAndSend("userNotFound", "u");

        // then
        assertThat(userDetails).isEqualTo(null);
    }
}