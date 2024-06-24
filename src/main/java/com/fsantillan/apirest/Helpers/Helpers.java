package com.fsantillan.apirest.Helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static String getDateTime() {
       LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    public static String getRole(String role) {
        String roleUser = "";
        switch (role) {
            case "ADMIN":
                roleUser = "ROLE_ADMIN";
                break;
            case "USER":
                roleUser = "ROLE_USER";
                break;
            default:
                roleUser = "ROLE_USER";
                break;
        }
        return roleUser;
    }

}
