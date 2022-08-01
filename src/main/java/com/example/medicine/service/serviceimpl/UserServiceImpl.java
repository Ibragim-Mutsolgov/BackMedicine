package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.User;
import com.example.medicine.repository.UserRepository;
import com.example.medicine.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
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

    private JmsTemplate jmsTemplate;

    @Override
    public List<User> findAll() {
        List<User> list = usersRepository.findAll();
        jmsTemplate.convertAndSend("userFindAll", true);
        return list;
    }

    @Override
    public User findById(Long id) {
        User user = usersRepository.findById(id).get();
        jmsTemplate.convertAndSend("userFindById", user);
        return user;
    }

    @Override
    public User save(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        jmsTemplate.convertAndSend("userSave", user);
        return user;
    }

    @Override
    public void delete(Long id) {
        User user = usersRepository.findById(id).get();
        usersRepository.deleteById(id);
        jmsTemplate.convertAndSend("userDelete", user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = usersRepository.findByUsername(username);
        if(user == null){
            jmsTemplate.convertAndSend("userNotFound", username);
            return null;
        } else{
            return user;
        }
    }
}