package com.fsantillan.apirest.Services;

import java.util.List;

import com.fsantillan.apirest.common.Dto.UserDto;
import com.fsantillan.apirest.common.Models.User;

public interface UserService {
    User CreateUser(UserDto user);
    UserDto GetUser(Integer id);
    List<UserDto> GetUsers();
    String UpdateUser(UserDto user);
    void DeleteUser(int id);

}
