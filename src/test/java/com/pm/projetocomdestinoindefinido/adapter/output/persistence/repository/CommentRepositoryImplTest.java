package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.CommentEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.CommentEntityMapper;
import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.CommentJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentRepositoryImplTest {
  @Mock CommentEntityMapper mapper; @Mock CommentJpaRepository jpa; @InjectMocks CommentRepositoryImpl repository;

  @Test void getSubCommentsMapsAllEntities() {
    UUID parent = UUID.randomUUID(); CommentEntity entity = new CommentEntity(); Comment model = new Comment();
    when(jpa.queryAllByParentCommentId(parent)).thenReturn(List.of(entity)); when(mapper.toModel(entity)).thenReturn(model);
    assertThat(repository.getSubComments(parent)).containsExactly(model);
  }

  @Test void createCommentMapsAndSaves() {
    Comment input = new Comment(); CommentEntity entity = new CommentEntity(); Comment saved = new Comment();
    when(mapper.toEntity(input)).thenReturn(entity); when(jpa.save(entity)).thenReturn(entity); when(mapper.toModel(entity)).thenReturn(saved);
    assertThat(repository.createComment(input)).isSameAs(saved); verify(jpa).save(entity);
  }
}
