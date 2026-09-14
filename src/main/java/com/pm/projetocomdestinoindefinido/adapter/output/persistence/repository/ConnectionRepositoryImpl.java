package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection.ConnectionEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.ConnectionEntityMapper;
import com.pm.projetocomdestinoindefinido.domain.model.Connection;
import com.pm.projetocomdestinoindefinido.domain.port.out.ConnectionRepository;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.ConnectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConnectionRepositoryImpl implements ConnectionRepository {

  private final ConnectionJpaRepository connectionJpaRepository;
  private final ConnectionEntityMapper connectionEntityMapper;

  @Override
  public List<Connection> getNodeConnections(UUID startNodeId) {
    return connectionJpaRepository.queryAllByStartNodeId(startNodeId).stream().map(connectionEntityMapper::toModel).toList();
  }

  @Override
  public Connection createConnection(Connection connection) {
    ConnectionEntity connectionEntity = connectionEntityMapper.toEntity(connection);

    ConnectionEntity savedConnectionEntity = connectionJpaRepository.save(connectionEntity);

    return connectionEntityMapper.toModel(savedConnectionEntity);
  }
}
