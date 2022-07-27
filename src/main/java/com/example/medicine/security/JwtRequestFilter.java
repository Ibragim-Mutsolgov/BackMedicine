package com.example.medicine.security;

import com.example.medicine.service.serviceimpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserServiceImpl userService;

    private final JwtUtil jwtUtil;

    private JmsTemplate jmsTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            try{
                username = jwtUtil.getUsernameFromToken(jwt);
            }catch(Exception e){
                jmsTemplate.convertAndSend("tokenIsInvalid", e.getMessage());
            }
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}