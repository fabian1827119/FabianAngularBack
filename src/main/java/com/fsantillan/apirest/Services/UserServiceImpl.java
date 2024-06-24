package com.fsantillan.apirest.Services;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fsantillan.apirest.Helpers.Helpers;
import com.fsantillan.apirest.Repositories.UserRepository;
import com.fsantillan.apirest.common.Dto.UserDto;
import com.fsantillan.apirest.common.Models.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    

    @Override
    public User CreateUser(UserDto user){
        try {

            User newUser = User.builder()
                .names(user.getNames())
                .lastName(user.getLastName())
                .secondLastName(user.getSecondLastName())
                .username(user.getUsername())
                // .password(passwordEncoder.encode(user.getPassword()))
                .password(user.getPassword())
                .roles(user.getRoles())
                .photoUser(user.getPhotoUserString())
                .creation(Helpers.getDateTime())
                .update(Helpers.getDateTime())
                .active(true)
                .build();

            User savedUser = userRepository.save(newUser);
            return savedUser;
            // System.out.println("User created");
        } catch (Exception e) {
            System.out.println("Error creating user");
        }
        return null;
    }



    @Override
    public UserDto GetUser(Integer id) {
        UserDto userDto = null;
        User user = userRepository.findById(id).orElse(null) ;
        if (user != null) {
             userDto = UserDto.builder()
                .id(user.getId())
                .names(user.getNames())
                .lastName(user.getLastName())
                .secondLastName(user.getSecondLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .photoUserString(user.getPhotoUser())
                .creation(user.getCreation())
                .update(user.getUpdate())
                .build();
        } 
        return userDto;  
    }



    @Override
    public List<UserDto> GetUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = null;
       
            usersDto = users.stream().map(user -> UserDto.builder()
                .id(user.getId())
                .names(user.getNames())
                .lastName(user.getLastName())
                .secondLastName(user.getSecondLastName())
                .username(user.getUsername())
                // .password(user.getPassword())
                .roles(user.getRoles())
                .photoUserString(user.getPhotoUser())
                .creation(user.getCreation())
                .update(user.getUpdate())
                .build()
            ).toList();
        return usersDto;
        // return users.stream().map(user -> UserDto.builder()
        //     .id(user.getId())
        //     .names(user.getNames())
        //     .lastName(user.getLastName())
        //     .secondLastName(user.getSecondLastName())
        //     .username(user.getUsername())
        //     // .password(user.getPassword())
        //     .roles(user.getRoles())
        //     .photoUser(user.getPhotoUser())
        //     .creation(user.getCreation())
        //     .update(user.getUpdate())
        //     .build()
        // ).toList();
        
        
    }



    @Override
    public String UpdateUser(UserDto user) {
        try {
            User userById = userRepository.findById(user.getId()).orElse(null);
    
            if (userById != null) {
                userById.setNames(user.getNames());
                userById.setLastName(user.getLastName());
                userById.setSecondLastName(user.getSecondLastName());
                userById.setUsername(user.getUsername());
                userById.setPassword(user.getPassword());
                userById.setRoles(user.getRoles());
                userById.setPhotoUser(user.getPhotoUserString());
                userById.setUpdate(Helpers.getDateTime());
                // userById.setActive(true);
    
                User savedUser = userRepository.save(userById);
                return "Usuario actualizado correctamente";
            } else {
                return "Usuario no encontrado";
            }
        } catch (Exception e) {
            System.out.println("Error actualizar usuario");
        }
        return null;
    }



    @Override
    public void DeleteUser(int id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                throw new RuntimeException("No se encontro el usuario");
            }else{
                userRepository.deleteById(user.getId());
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }



}
