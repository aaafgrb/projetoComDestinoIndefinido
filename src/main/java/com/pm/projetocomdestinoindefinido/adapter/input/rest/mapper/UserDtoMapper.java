package com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.UserRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.UserResponseDTO;
import com.pm.projetocomdestinoindefinido.domain.model.User;

public class UserDtoMapper {
  public static User toModel(UserRequestDTO userRequestDTO){
    if(userRequestDTO == null) return null;

    User user = new User();
    user.setName(userRequestDTO.getName());
    return user;
  }

  public static UserResponseDTO toDto(User user) {
    if(user == null) return null;

    UserResponseDTO userResponseDTO = new UserResponseDTO();
    userResponseDTO.setId(user.getId());
    userResponseDTO.setName(user.getName());
    return userResponseDTO;
  }
}
