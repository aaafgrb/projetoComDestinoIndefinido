package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.Connection;
import com.pm.projetocomdestinoindefinido.domain.port.out.ConnectionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConnectionServiceTest {
  @Mock ConnectionRepository repository;
  @InjectMocks ConnectionService service;

  @AfterEach void clearContext() { SecurityContextHolder.clearContext(); }

  @Test void getNodeConnectionsDelegates() {
    UUID nodeId = UUID.randomUUID(); List<Connection> expected = List.of(new Connection());
    when(repository.getNodeConnections(nodeId)).thenReturn(expected);
    assertThat(service.getNodeConnections(nodeId)).isSameAs(expected);
  }

  @Test void createConnectionUsesAuthenticatedUser() {
    UUID userId = UUID.randomUUID(), start = UUID.randomUUID(), end = UUID.randomUUID();
    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(userId.toString(), null));
    Connection saved = new Connection(); when(repository.createConnection(any())).thenReturn(saved);
    assertThat(service.createConnection(start, end)).isSameAs(saved);
    var captor = org.mockito.ArgumentCaptor.forClass(Connection.class);
    verify(repository).createConnection(captor.capture());
    assertThat(captor.getValue().getStartNodeId()).isEqualTo(start);
    assertThat(captor.getValue().getEndNodeId()).isEqualTo(end);
    assertThat(captor.getValue().getCreatorUserId()).isEqualTo(userId);
  }
}
