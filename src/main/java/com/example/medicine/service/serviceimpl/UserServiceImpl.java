package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.User;
import com.example.medicine.repository.UserRepository;
import com.example.medicine.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository usersRepository;

    @Override
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(usersRepository.findAll());
    }

    @Override
    public ResponseEntity<User> findById(Long id) {
        return usersRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<User> save(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(usersRepository.save(user));
    }

    @Override
    public ResponseEntity<User> delete(Long id) {
        return usersRepository.findById(id)
                .map(user -> {
                    usersRepository.deleteById(id);
                    return ResponseEntity.ok().body(user);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = usersRepository.findByUsername(username);
        if(user == null){
            return null;
        } else{
            return user;
        }
    }
}