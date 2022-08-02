package com.example.medicine.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long time;

    private  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token){
        return !isTokenExpired(token);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("role", roleList);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject){
        Date issuedDate = new Date();
        if(time == null){
            time = 3000L;
        }
        if(secret == null){
            secret = "abcd";
        }
        Date expiredDate = new Date(issuedDate.getTime() + 60 * 60 * time);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token){
        Date date = getExpirationDateFromToken(token);
        return date != null && date.before(new Date());
    }
}
