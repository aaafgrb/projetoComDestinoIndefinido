package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.model.UserAuthDetails;
import com.pm.projetocomdestinoindefinido.domain.port.out.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthServiceTest {
  @Mock UserRepository repository; @InjectMocks UserAuthService service;

  @Test void loadsUserDetailsByUuidString() {
    UUID id = UUID.randomUUID(); User user = new User(); user.setId(id); user.setPassword("p"); user.setRoles("ROLE_USER");
    when(repository.getUserById(id)).thenReturn(Optional.of(user));
    assertThat(service.loadUserByUsername(id.toString())).isInstanceOf(UserAuthDetails.class);
  }

  @Test void throwsWhenUserDoesNotExist() {
    UUID id = UUID.randomUUID(); when(repository.getUserById(id)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> service.loadUserByUsername(id.toString()))
        .isInstanceOf(UsernameNotFoundException.class).hasMessageContaining(id.toString());
  }
}
