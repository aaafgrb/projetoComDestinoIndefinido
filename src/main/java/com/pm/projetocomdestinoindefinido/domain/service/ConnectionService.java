package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.Connection;
import com.pm.projetocomdestinoindefinido.domain.port.in.ConnectionUseCase;
import com.pm.projetocomdestinoindefinido.domain.port.out.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConnectionService implements ConnectionUseCase {

  private final ConnectionRepository connectionRepository;

  @Override
  public List<Connection> getNodeConnections(UUID startNodeId) {
    return connectionRepository.getNodeConnections(startNodeId);
  }

  @Override
  public Connection createConnection(UUID startNodeId, UUID endNodeId) {
    String userIdStr = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
    Connection connection = new Connection();
    connection.setStartNodeId(startNodeId);
    connection.setEndNodeId(endNodeId);
    connection.setCreatorUserId(UUID.fromString(userIdStr));

    return connectionRepository.createConnection(connection);
  }
}
