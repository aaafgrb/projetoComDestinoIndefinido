package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.NodeEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.NodeEntityMapper;
import com.pm.projetocomdestinoindefinido.domain.model.Node;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.NodeJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NodeRepositoryImplTest {
  @Mock NodeJpaRepository jpa; @Mock NodeEntityMapper mapper; @InjectMocks NodeRepositoryImpl repository;

  @Test void findByIdMapsPresentEntity() {
    UUID id = UUID.randomUUID(); NodeEntity entity = new NodeEntity(); Node model = new Node();
    when(jpa.findById(id)).thenReturn(Optional.of(entity)); when(mapper.toModel(entity)).thenReturn(model);
    assertThat(repository.findById(id)).containsSame(model);
  }

  @Test void createNodeSavesMappedEntity() {
    Node input = new Node(); NodeEntity entity = new NodeEntity(); Node output = new Node();
    when(mapper.toEntity(input)).thenReturn(entity); when(jpa.save(entity)).thenReturn(entity); when(mapper.toModel(entity)).thenReturn(output);
    assertThat(repository.createNode(input)).isSameAs(output);
  }
}
