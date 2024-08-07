package com.fsantillan.apirest.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsantillan.apirest.Services.AuthService;
import com.fsantillan.apirest.common.Dto.RequestLogin;
import com.fsantillan.apirest.common.Dto.UserDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


@PostMapping("/login")    
public ResponseEntity<String>login(@RequestBody RequestLogin requestLogin) {
    try {
        return ResponseEntity.ok("Usuario logueado correctamente");
    } catch (RuntimeException ex) {        
        return new ResponseEntity<>("Ha ocurrido un error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

public String logout() {
    return "Logout";  
}

@PostMapping("/register")
public ResponseEntity<String> register(@RequestBody UserDto requestLogin) {
    try {
        String data= authService.register(requestLogin);
        

        return ResponseEntity.ok(data);     
    } catch (RuntimeException ex) {
        return new ResponseEntity<>("Ha ocurrido un error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
     
}



}
