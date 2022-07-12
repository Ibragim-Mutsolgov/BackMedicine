package com.example.medicine.initial;

import com.example.medicine.domain.Role;
import com.example.medicine.domain.User;
import com.example.medicine.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsersInitial implements CommandLineRunner {

    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        userService.save(
//                new User(
//                        "user",
//                        "password",
//                        Role.USER
//                )
//        );
    }
}
