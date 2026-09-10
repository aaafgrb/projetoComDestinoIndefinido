package com.pm.projetocomdestinoindefinido.domain.port.out;

import com.pm.projetocomdestinoindefinido.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  Optional<User> getUserById(UUID id);
  User createUser(User user);
  User updateUser(User user);
  void deleteUser(UUID id);
}
