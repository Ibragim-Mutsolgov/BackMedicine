package com.example.medicine.initial;

import com.example.medicine.model.Role;
import com.example.medicine.model.User;
import com.example.medicine.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UsersInitializing implements CommandLineRunner {

    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if(repository.findAll().size() == 0) {
            repository.save(
                    new User(
                            "user",
                            "password",
                            Role.USER
                    )
            );
            log.info("IN UsersInitial: ADMIN IS CREATED");
        }
    }
}
