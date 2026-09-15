package com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tb_comment")
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  UUID id;

  @Nullable
  UUID parentCommentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_user_id")
  @NotNull
  UserEntity creatorUser;

  @NotNull
  String content;
}
