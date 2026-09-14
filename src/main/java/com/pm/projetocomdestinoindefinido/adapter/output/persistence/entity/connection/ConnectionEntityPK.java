package com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class ConnectionEntityPK implements Serializable {
  UUID startNodeId;
  UUID endNodeId;
  UUID creatorUserId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ConnectionEntityPK connectionPK)) return false;
    return  Objects.equals(startNodeId, connectionPK.startNodeId)
            && Objects.equals(endNodeId, connectionPK.endNodeId)
            && Objects.equals(creatorUserId, connectionPK.creatorUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startNodeId, endNodeId, creatorUserId);
  }
}