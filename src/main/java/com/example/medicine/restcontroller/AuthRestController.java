package com.example.medicine.restcontroller;

import com.example.medicine.security.JwtResponse;
import com.example.medicine.domain.User;
import com.example.medicine.security.JwtUtil;
import com.example.medicine.service.serviceimpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {

    private final JwtUtil jwtUtil;

    private final UserServiceImpl userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createToken(@RequestBody User user) throws Exception {
        try {
            authenticate(user.getUsername(), user.getPassword());
            UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch(Exception ex){
            throw new Exception("INCORRECT USERNAME OR PASSWORD", ex);
        }
    }

    private void authenticate(String username, String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
