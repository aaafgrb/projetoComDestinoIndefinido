package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.UserEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.UserEntityMapper;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepositoryImplTest {
  @Mock UserJpaRepository jpa; @Mock UserEntityMapper mapper; @InjectMocks UserRepositoryImpl repository;

  @Test void getUserByIdMapsFoundEntity() {
    UUID id = UUID.randomUUID(); UserEntity entity = new UserEntity(); User user = new User();
    when(jpa.findById(id)).thenReturn(Optional.of(entity)); when(mapper.toModel(entity)).thenReturn(user);
    assertThat(repository.getUserById(id)).containsSame(user);
  }

  @Test void getUserByIdReturnsEmptyWhenMissing() { UUID id = UUID.randomUUID(); when(jpa.findById(id)).thenReturn(Optional.empty()); assertThat(repository.getUserById(id)).isEmpty(); }

  @Test void updateUserReturnsNullWhenEntityMissing() {
    User user = new User(); user.setId(UUID.randomUUID()); when(jpa.findById(user.getId())).thenReturn(Optional.empty());
    assertThat(repository.updateUser(user)).isNull(); verify(jpa, never()).save(any());
  }

  @Test void updateUserChangesNameAndSavesManagedEntity() {
    UUID id = UUID.randomUUID(); User user = new User(); user.setId(id); user.setName("new");
    UserEntity entity = new UserEntity(); entity.setName("old"); User mapped = new User();
    when(jpa.findById(id)).thenReturn(Optional.of(entity)); when(jpa.save(entity)).thenReturn(entity); when(mapper.toModel(entity)).thenReturn(mapped);
    assertThat(repository.updateUser(user)).isSameAs(mapped); assertThat(entity.getName()).isEqualTo("new");
  }

  @Test void deleteUserDelegates() { UUID id = UUID.randomUUID(); repository.deleteUser(id); verify(jpa).deleteById(id); }
}
