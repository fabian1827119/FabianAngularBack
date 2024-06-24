package com.fsantillan.apirest.common.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "users",
    uniqueConstraints = {@UniqueConstraint(columnNames = "email")}
    )   

public class User implements UserDetails {

    @Id
    @GeneratedValue
    Integer id; 
    String names;
    String lastName;
    String secondLastName;
    @Column(nullable = false)
    String username;
    String password;
    String roles;
    @Lob
    @Column(length = 1000000)
    String photoUser;
    String creation;
    String update;
    boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return List.of(new SimpleGrantedAuthority((role.name())));
        return List.of(new SimpleGrantedAuthority((roles)));
    }



}
