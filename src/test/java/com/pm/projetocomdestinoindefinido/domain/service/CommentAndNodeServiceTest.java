package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.*;
import com.pm.projetocomdestinoindefinido.domain.port.in.CommentUseCase;
import com.pm.projetocomdestinoindefinido.domain.port.out.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentAndNodeServiceTest {
  @Mock UserRepository userRepository; @Mock CommentRepository commentRepository;
  @Mock NodeRepository nodeRepository; @Mock CommentUseCase commentUseCase;
  @InjectMocks CommentService commentService; @InjectMocks NodeService nodeService;

  @Test void getSubCommentsDelegates() {
    UUID id = UUID.randomUUID(); List<Comment> comments = List.of(new Comment());
    when(commentRepository.getSubComments(id)).thenReturn(comments);
    assertThat(commentService.getSubComments(id)).isSameAs(comments);
  }

  @Test void createCommentCopiesContentParentAndAuthenticatedUser() {
    UUID userId = UUID.randomUUID(), parent = UUID.randomUUID(); User user = new User();
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId.toString(), null));
    when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));
    Comment saved = new Comment(); when(commentRepository.createComment(any())).thenReturn(saved);
    assertThat(commentService.createComment("hello", parent)).isSameAs(saved);
    ArgumentCaptor<Comment> captor = ArgumentCaptor.forClass(Comment.class); verify(commentRepository).createComment(captor.capture());
    assertThat(captor.getValue().getContent()).isEqualTo("hello");
    assertThat(captor.getValue().getParentCommentId()).isEqualTo(parent);
    assertThat(captor.getValue().getCreatorUser()).isSameAs(user);
    SecurityContextHolder.clearContext();
  }

  @Test void getNodeReturnsNullWhenRepositoryHasNoNode() {
    UUID id = UUID.randomUUID(); when(nodeRepository.findById(id)).thenReturn(Optional.empty());
    assertThat(nodeService.getNode(id)).isNull();
  }

  @Test void createNodeBuildsNodeFromRootComment() {
    User user = new User(); Comment comment = new Comment(); comment.setCreatorUser(user);
    when(commentUseCase.createComment("content", null)).thenReturn(comment);
    Node saved = new Node(); when(nodeRepository.createNode(any())).thenReturn(saved);
    assertThat(nodeService.createNode("content")).isSameAs(saved);
    ArgumentCaptor<Node> captor = ArgumentCaptor.forClass(Node.class); verify(nodeRepository).createNode(captor.capture());
    assertThat(captor.getValue().getComment()).isSameAs(comment);
    assertThat(captor.getValue().getCreatorUser()).isSameAs(user);
  }
}
