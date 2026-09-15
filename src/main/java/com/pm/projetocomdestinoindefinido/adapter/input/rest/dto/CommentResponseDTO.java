package com.pm.projetocomdestinoindefinido.adapter.input.rest.dto;

import com.pm.projetocomdestinoindefinido.domain.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class CommentResponseDTO {
  UUID parentCommentId;
  UUID id;
  UserResponseDTO creatorUser;
  String content;
}
