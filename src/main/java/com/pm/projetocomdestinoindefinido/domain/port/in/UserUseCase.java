package com.pm.projetocomdestinoindefinido.domain.port.in;

import com.pm.projetocomdestinoindefinido.domain.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserUseCase {
  User getUserById(UUID id);
  User createUser(User user);
  User updateUser(UUID id, User user);
  void deleteUser(UUID id);
}
