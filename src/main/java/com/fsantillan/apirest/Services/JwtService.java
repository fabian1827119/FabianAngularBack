package com.fsantillan.apirest.Services;

import org.springframework.security.core.userdetails.UserDetails;

import com.fsantillan.apirest.common.Models.User;

public interface JwtService {
    String getToken(User user);
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

}