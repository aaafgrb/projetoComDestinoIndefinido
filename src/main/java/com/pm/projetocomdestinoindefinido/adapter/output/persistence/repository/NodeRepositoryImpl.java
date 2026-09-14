package com.pm.projetocomdestinoindefinido.adapter.output.persistence.repository;

import com.pm.projetocomdestinoindefinido.domain.model.Node;
import com.pm.projetocomdestinoindefinido.domain.port.out.NodeRepository;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.NodeEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper.NodeEntityMapper;
import com.pm.projetocomdestinoindefinido.infrastructure.persistence.NodeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NodeRepositoryImpl implements NodeRepository {

  private final NodeJpaRepository repository;
  private final NodeEntityMapper nodeEntityMapper;

  @Override
  public Node createNode(Node node) {
    NodeEntity entity = nodeEntityMapper.toEntity(node);

    NodeEntity saved = repository.save(entity);

    return nodeEntityMapper.toModel(saved);
  }

  @Override
  public Optional<Node> findById(UUID id) {
    return repository.findById(id)
            .map(nodeEntityMapper::toModel);
  }

}
