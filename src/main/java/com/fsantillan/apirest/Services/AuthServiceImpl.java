package com.fsantillan.apirest.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.fsantillan.apirest.Helpers.Helpers;
import com.fsantillan.apirest.Repositories.UserRepository;
import com.fsantillan.apirest.common.Dto.UserDto;
import com.fsantillan.apirest.common.Models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    public String login(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public String register(UserDto requestLogin) {
        if (userRepository.findByUsername(requestLogin.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }
        User newUser = new User();
        newUser.setNames(requestLogin.getNames());
        newUser.setLastName(requestLogin.getLastName());
        newUser.setSecondLastName(requestLogin.getSecondLastName());
        newUser.setRoles(requestLogin.getRoles());
        newUser.setUsername(requestLogin.getUsername());
        newUser.setPassword(requestLogin.getPassword());
        newUser.setCreation(Helpers.getDateTime());
        newUser.setUpdate(Helpers.getDateTime());
        userRepository.save(newUser);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(requestLogin.getUsername());
        String token = jwtService.getToken(newUser);
        return jwtService.getToken(newUser);



        // return "Usuario registrado correctamente";
        
    }

    @Override
    public String logout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

}
