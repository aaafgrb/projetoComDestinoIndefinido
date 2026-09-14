package com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tb_connection")
@IdClass( ConnectionEntityPK.class )
public class ConnectionEntity {
  @Id
  UUID startNodeId;
  @Id
  UUID endNodeId;
  @Id
  UUID creatorUserId;
}
