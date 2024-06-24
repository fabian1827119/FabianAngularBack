package com.fsantillan.apirest.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fsantillan.apirest.common.Models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
        
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer id);

}
