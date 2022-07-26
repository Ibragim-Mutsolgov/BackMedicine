package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.User;
import com.example.medicine.repository.UserRepository;
import com.example.medicine.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository usersRepository;

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        User user = usersRepository.findById(id).get();
        log.info("IN UserServiceImpl: USER " + user.getUsername() + " FOUND");
        return user;
    }

    @Override
    public User save(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        log.info("IN UserServiceImpl: USER " + user.getUsername() + " SAVED");
        return user;
    }

    @Override
    public void delete(Long id) {
        User user = usersRepository.findById(id).get();
        usersRepository.deleteById(id);
        log.info("IN UserServiceImpl: USER " + user.getUsername() + " DELETED");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = usersRepository.findByUsername(username);
        if(users == null){
            log.info("IN UsersServiceImpl: USER " + username + " NOT FOUND IN DATABASE");
        } else{
            log.info("IN UsersServiceImpl: USER " + username + " FOUND IN DATABASE");
            return new User(users.getUsername(), users.getPassword(), users.getRole());
        }
        throw new UsernameNotFoundException("IN UsersServiceImpl: USER " + username + " NOT FOUND IN DATABASE");
    }
}