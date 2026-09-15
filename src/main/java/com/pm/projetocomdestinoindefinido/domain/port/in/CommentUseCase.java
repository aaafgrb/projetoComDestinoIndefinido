package com.pm.projetocomdestinoindefinido.domain.port.in;

import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface CommentUseCase {
  List<Comment> getSubComments(UUID parentCommentId);

  Comment createComment(@NonNull String content, @Nullable UUID parentCommentId);
}
