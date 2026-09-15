package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.port.in.CommentUseCase;
import com.pm.projetocomdestinoindefinido.domain.port.out.CommentRepository;
import com.pm.projetocomdestinoindefinido.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

  private final UserRepository userRepository;
  private final CommentRepository commentRepository;

  @Override
  public List<Comment> getSubComments(UUID getSubComments) {
    return commentRepository.getSubComments(getSubComments);
  }

  @Override
  public Comment createComment(@NonNull String content, @Nullable UUID parentCommentId) {
    String userIdStr = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
    User user = userRepository.getUserById(UUID.fromString(userIdStr)).orElse(null);

    Comment comment = new Comment();
    comment.setContent(content);
    comment.setCreatorUser(user);
    comment.setParentCommentId(parentCommentId);

    return commentRepository.createComment(comment);
  }
}
