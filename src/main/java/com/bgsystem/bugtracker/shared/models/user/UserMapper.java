package com.bgsystem.bugtracker.shared.models.user;

import com.bgsystem.bugtracker.shared.mapper.DefaultMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper implements DefaultMapper <UserDTO, UserMiniDTO, UserForm, User> {

    @Override
    public UserDTO toDTO(User entity) {

        if (entity == null) return null;

        return UserDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .roles(entity.getRoles())
                .build();
    }

    @Override
    public UserMiniDTO toSmallDTO(User entity) {

        if (entity == null) return null;

        return UserMiniDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .username(entity.getUsername())
                .roles(entity.getRoles())
                .build();
    }

    @Override
    public User toEntity(UserForm form) {

        if (form == null) return null;

        return User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .username(form.getUsername())
                .roles(form.getRoles())
                .password(form.getPassword())
                .build();
    }
}
