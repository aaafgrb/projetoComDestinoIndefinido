package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.CommentEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.CommentEntityMapper;
import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import com.pm.projetocomdestinoindefinido.domain.port.out.CommentRepository;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.CommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
  private final CommentEntityMapper commentEntityMapper;
  private final CommentJpaRepository commentJpaRepository;

  @Override
  public List<Comment> getSubComments(UUID parentCommentId) {
    return commentJpaRepository.queryAllByParentCommentId(parentCommentId).stream().map(commentEntityMapper::toModel).toList();
  }

  @Override
  public Comment createComment(Comment comment) {
    CommentEntity commentEntity = commentJpaRepository.save(commentEntityMapper.toEntity(comment));
    return commentEntityMapper.toModel(commentEntity);
  }
}
