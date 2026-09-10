package com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.UserEntity;
import com.pm.projetocomdestinoindefinido.domain.model.User;

public class UserEntityMapper {
  public static User toModel(UserEntity userEntity) {
    User user = new User();
    user.setId(userEntity.getId());
    user.setName(userEntity.getName());
    return user;
  }

  public static UserEntity toEntity(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(user.getId());
    userEntity.setName(user.getName());
    return userEntity;
  }
}
