package com.pm.projetocomdestinoindefinido.domain.port.out;

import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface CommentRepository {
  List<Comment> getSubComments(UUID parentCommentId);
  Comment createComment(Comment comment);
}
