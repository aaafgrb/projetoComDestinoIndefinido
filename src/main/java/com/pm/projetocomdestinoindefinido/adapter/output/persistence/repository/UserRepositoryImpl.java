package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.UserEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.UserEntityMapper;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.port.out.UserRepository;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final UserJpaRepository userJpaRepository;

  @Override
  public Optional<User> getUserById(UUID id) {
    return userJpaRepository.findById(id).map(UserEntityMapper::toModel);
  }

  @Override
  public User createUser(User user) {
    UserEntity userEntity = UserEntityMapper.toEntity(user);
    UserEntity savedUserEntity = userJpaRepository.save(userEntity);
    return UserEntityMapper.toModel(savedUserEntity);
  }

  @Override
  public User updateUser(User user) {
    Optional<UserEntity> maybeUserEntity = userJpaRepository.findById(user.getId());
    if(maybeUserEntity.isEmpty()) return null;
    UserEntity managedUserEntity = maybeUserEntity.get();
    managedUserEntity.setName(user.getName());

    UserEntity savedUserEntity = userJpaRepository.save(managedUserEntity);

    return UserEntityMapper.toModel(savedUserEntity);
  }

  @Override
  public void deleteUser(UUID id) {
    userJpaRepository.deleteById(id);
  }
}
