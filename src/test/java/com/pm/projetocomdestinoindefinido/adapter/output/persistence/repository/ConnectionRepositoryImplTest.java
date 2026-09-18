package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection.ConnectionEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.ConnectionEntityMapper;
import com.pm.projetocomdestinoindefinido.domain.model.Connection;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.ConnectionJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConnectionRepositoryImplTest {
  @Mock ConnectionJpaRepository jpa; @Mock ConnectionEntityMapper mapper; @InjectMocks ConnectionRepositoryImpl repository;

  @Test void getNodeConnectionsMapsEntities() {
    UUID node = UUID.randomUUID(); ConnectionEntity entity = new ConnectionEntity(); Connection model = new Connection();
    when(jpa.queryAllByStartNodeId(node)).thenReturn(List.of(entity)); when(mapper.toModel(entity)).thenReturn(model);
    assertThat(repository.getNodeConnections(node)).containsExactly(model);
  }

  @Test void createConnectionSavesMappedEntity() {
    Connection input = new Connection(); ConnectionEntity entity = new ConnectionEntity(); Connection output = new Connection();
    when(mapper.toEntity(input)).thenReturn(entity); when(jpa.save(entity)).thenReturn(entity); when(mapper.toModel(entity)).thenReturn(output);
    assertThat(repository.createConnection(input)).isSameAs(output);
  }
}
