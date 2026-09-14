package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.Node;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.port.in.NodeUseCase;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.NodeEntity;
import com.pm.projetocomdestinoindefinido.domain.port.out.NodeRepository;
import com.pm.projetocomdestinoindefinido.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NodeService implements NodeUseCase {

  private final NodeRepository nodeRepository;
  private final UserRepository userRepository;

  @Override
  public Node getNode(UUID id) {
    Optional<Node> maybeNode = nodeRepository.findById(id);
    return maybeNode.orElse(null);
  }

  @Override
  public Node createNode(Node node) {
    String userIdStr = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
    User user = userRepository.getUserById(UUID.fromString(userIdStr)).orElse(null);

    node.setCreatorUser(user);

    return nodeRepository.createNode(node);
  }
}
