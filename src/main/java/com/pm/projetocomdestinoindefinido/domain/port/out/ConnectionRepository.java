package com.pm.projetocomdestinoindefinido.domain.port.out;

import com.pm.projetocomdestinoindefinido.domain.model.Connection;

import java.util.List;
import java.util.UUID;

public interface ConnectionRepository {
  List<Connection> getNodeConnections(UUID startNodeId);
  Connection createConnection(Connection connection);
}
