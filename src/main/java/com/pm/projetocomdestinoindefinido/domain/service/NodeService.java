package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import com.pm.projetocomdestinoindefinido.domain.model.Node;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.port.in.CommentUseCase;
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
  private final CommentUseCase commentUseCase;

  @Override
  public Node getNode(UUID id) {
    Optional<Node> maybeNode = nodeRepository.findById(id);
    return maybeNode.orElse(null);
  }

  @Override
  public Node createNode(String content) {
    Comment comment = commentUseCase.createComment(content, null);
    Node node = new Node();
    node.setCreatorUser(comment.getCreatorUser());
    node.setComment(comment);

    return nodeRepository.createNode(node);
  }
}
