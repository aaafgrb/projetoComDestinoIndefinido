package com.pm.projetocomdestinoindefinido.infrastructure.persistence;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection.ConnectionEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection.ConnectionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConnectionJpaRepository extends JpaRepository<ConnectionEntity, ConnectionEntityPK> {
  List<ConnectionEntity> queryAllByStartNodeId(UUID startNodeId);
}
