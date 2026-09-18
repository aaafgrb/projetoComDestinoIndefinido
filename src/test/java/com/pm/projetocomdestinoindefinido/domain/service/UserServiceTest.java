package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.port.out.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock UserRepository repository;
  @Mock PasswordEncoder passwordEncoder;
  @InjectMocks UserService service;

  @Test void getUserByIdReturnsUserWhenFound() {
    UUID id = UUID.randomUUID();
    User user = new User(); user.setId(id);
    when(repository.getUserById(id)).thenReturn(Optional.of(user));
    assertThat(service.getUserById(id)).isSameAs(user);
  }

  @Test void getUserByIdReturnsNullWhenMissing() {
    UUID id = UUID.randomUUID();
    when(repository.getUserById(id)).thenReturn(Optional.empty());
    assertThat(service.getUserById(id)).isNull();
  }

  @Test void createUserEncodesPasswordAndAssignsDefaultRole() {
    User user = new User(); user.setPassword("plain");
    when(passwordEncoder.encode("plain")).thenReturn("encoded");
    when(repository.createUser(user)).thenReturn(user);
    assertThat(service.createUser(user)).isSameAs(user);
    assertThat(user.getPassword()).isEqualTo("encoded");
    assertThat(user.getRoles()).isEqualTo("ROLE_USER");
    verify(repository).createUser(user);
  }

  @Test void updateUserSetsIdAndEncodesPassword() {
    UUID id = UUID.randomUUID(); User user = new User(); user.setPassword("plain");
    when(passwordEncoder.encode("plain")).thenReturn("encoded");
    when(repository.updateUser(user)).thenReturn(user);
    service.updateUser(id, user);
    assertThat(user.getId()).isEqualTo(id);
    assertThat(user.getPassword()).isEqualTo("encoded");
  }

  @Test void deleteUserDelegatesToRepository() {
    UUID id = UUID.randomUUID();
    service.deleteUser(id);
    verify(repository).deleteUser(id);
  }
}
