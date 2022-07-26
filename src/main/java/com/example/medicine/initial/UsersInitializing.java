package com.example.medicine.initial;

import com.example.medicine.domain.Role;
import com.example.medicine.domain.User;
import com.example.medicine.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UsersInitializing implements CommandLineRunner {

    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if(userService.findAll().size() == 0) {
            userService.save(
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
