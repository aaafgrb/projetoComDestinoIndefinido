package com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tb_node")
public class NodeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_user_id")
  @NotNull
  UserEntity creatorUserEntity;

  @NotNull
  String content;
}
