package com.fsantillan.apirest.Controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsantillan.apirest.Services.UserService;
import com.fsantillan.apirest.common.Dto.UserDto;
import com.fsantillan.apirest.common.Models.User;

import lombok.RequiredArgsConstructor;
// import lombok.var;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersControllers {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity< List<UserDto> > list() {
        List<UserDto> users = userService.GetUsers();
        return ResponseEntity.ok(users);
        // return "List of users";
    }

    @GetMapping(value="/{id}") 
    public ResponseEntity<UserDto> detail(@PathVariable("id") int id) {
        UserDto user = userService.GetUser(id);
        return ResponseEntity.ok(user);
        
        // return "Detail of user with id: " + id;
    }

    @PostMapping("/create")
    // public ResponseEntity<User> create(@RequestBody UserDto user) {
    public ResponseEntity<User> create(
        @RequestParam("users") String users,
            // @RequestParam("id") String id,
            //  @RequestParam("names") String names,
            // @RequestParam("lastName") String lastName,
            // @RequestParam("secondLastName") String secondLastName,
            // @RequestParam("username") String username,
            // @RequestParam("roles") String roles,
            // @RequestParam("creation") String creation,
            @RequestParam("photoUser") MultipartFile photoUser) throws IOException{

                ObjectMapper objectMapper=new ObjectMapper();
                UserDto user = objectMapper.readValue(users, UserDto.class);

                byte[] phothoUserByte = photoUser.getBytes();
                String photoEncode = Base64.getEncoder().encodeToString(phothoUserByte);

                user.setPhotoUserString(photoEncode);

                
        // userService.CreateUser(user);
        // UserDto user333 = new UserDto();
        User createdUser = userService.CreateUser(user);
        return ResponseEntity.ok(createdUser);
        
        // return  ResponseEntity.ok(userService.CreateUser(user));
        // return  ResponseEntity.ok("User created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") int id, @RequestParam("users") String users,
            @RequestParam("photoUser") MultipartFile photoUser) throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                UserDto user=objectMapper.readValue(users, UserDto.class);

                byte[] phothoUserByte = photoUser.getBytes();
                String photoEncode = Base64.getEncoder().encodeToString(phothoUserByte);

                user.setPhotoUserString(photoEncode);

                String msg = userService.UpdateUser(user);
        return ResponseEntity.ok(msg);
    }

    // @DeleteMapping("/users/{id}")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {

        try {
            userService.DeleteUser(id);
            return ResponseEntity.ok("Delete user with id: " + id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
