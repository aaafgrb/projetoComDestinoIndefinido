package com.pm.projetocomdestinoindefinido.adapter.input.rest.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CommentRequestDTO {
  @NonNull
  String content;

  @NonNull
  String parentCommentId;
}
