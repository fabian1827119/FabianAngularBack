package com.fsantillan.apirest.Services;

import com.fsantillan.apirest.common.Dto.UserDto;

public interface AuthService {

    String login(String username, String password);
    String register(UserDto requestLogin);
    String logout();

}
