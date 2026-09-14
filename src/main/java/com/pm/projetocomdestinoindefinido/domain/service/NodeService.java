package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.Node;
import com.pm.projetocomdestinoindefinido.domain.port.in.NodeUseCase;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.NodeEntity;
import com.pm.projetocomdestinoindefinido.domain.port.out.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NodeService implements NodeUseCase {

  private final NodeRepository nodeRepository;

  @Override
  public Node getNode(UUID id) {
    Optional<Node> maybeNode = nodeRepository.findById(id);
    return maybeNode.orElse(null);
  }

  @Override
  public Node createNode(Node node) {
    return nodeRepository.createNode(node);
  }
}
