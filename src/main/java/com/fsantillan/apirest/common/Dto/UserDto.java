package com.fsantillan.apirest.common.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Integer id;
    String names;
    String lastName;
    String secondLastName;
    String username;
    String password;
    String roles;
    String photoUserString;
    String creation;
    String update;
    private MultipartFile photoUser;
}
