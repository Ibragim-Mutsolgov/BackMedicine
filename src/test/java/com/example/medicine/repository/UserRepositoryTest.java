package com.example.medicine.repository;

import com.example.medicine.domain.Role;
import com.example.medicine.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsername() {
        // given
        User user = new User(
                "user",
                "password",
                Role.USER);
        repository.save(user);

        // when
        User userSave = repository.findByUsername(user.getUsername());

        // then
        assertEquals(user, userSave);
    }
}