package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.port.in.UserUseCase;
import com.pm.projetocomdestinoindefinido.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

  private final UserRepository userRepository;

  @Override
  public User getUserById(UUID id) {
    Optional<User> maybeUser = userRepository.getUserById(id);
    return maybeUser.orElse(null);
  }

  @Override
  public User createUser(User user) {
    return userRepository.createUser(user);
  }

  @Override
  public User updateUser(UUID id, User user) {
    user.setId(id);
    return userRepository.updateUser(user);
  }

  @Override
  public void deleteUser(UUID id) {
    userRepository.deleteUser(id);
  }
}
