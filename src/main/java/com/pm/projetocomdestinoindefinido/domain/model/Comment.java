package com.pm.projetocomdestinoindefinido.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Comment {
  UUID parentCommentId;
  UUID id;
  User creatorUser;
  String content;
}
