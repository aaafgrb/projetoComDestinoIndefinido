package com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.UserRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.UserResponseDTO;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
  User toModel(UserRequestDTO userRequestDTO);

  UserResponseDTO toDto(User user);
}
