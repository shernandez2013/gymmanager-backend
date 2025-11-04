package com.mycompany.gymmanager.mapper;

import com.mycompany.gymmanager.dto.UserDTO;
import com.mycompany.gymmanager.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User entity);
    User toEntity(UserDTO dto);
    List<UserDTO> toDtoList(List<User> users);
}

