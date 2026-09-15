package com.pm.projetocomdestinoindefinido.infrastructure.persistence;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.CommentEntity;
import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, UUID> {
  List<CommentEntity> queryAllByParentCommentId(UUID parentCommentId);
}
