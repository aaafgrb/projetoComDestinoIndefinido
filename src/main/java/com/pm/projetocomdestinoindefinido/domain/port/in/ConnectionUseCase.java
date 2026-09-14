package com.pm.projetocomdestinoindefinido.domain.port.in;

import com.pm.projetocomdestinoindefinido.domain.model.Connection;

import java.util.List;
import java.util.UUID;

public interface ConnectionUseCase {
  List<Connection> getNodeConnections(UUID startNodeId);
  Connection createConnection(UUID startNodeId, UUID endNodeId);
}
