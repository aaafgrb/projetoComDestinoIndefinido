package com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.UserEntity;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
  User toModel(UserEntity userEntity);

  UserEntity toEntity(User user);
}
