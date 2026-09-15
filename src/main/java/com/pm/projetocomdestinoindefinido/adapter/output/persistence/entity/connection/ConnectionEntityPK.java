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
    if (!(o instanceof ConnectionEntityPK connectionEntityPK)) return false;
    return  Objects.equals(startNodeId, connectionEntityPK.startNodeId)
            && Objects.equals(endNodeId, connectionEntityPK.endNodeId)
            && Objects.equals(creatorUserId, connectionEntityPK.creatorUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startNodeId, endNodeId, creatorUserId);
  }
}