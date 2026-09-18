package com.pm.projetocomdestinoindefinido.adapter.input.rest.controller;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.*;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper.*;
import com.pm.projetocomdestinoindefinido.domain.model.*;
import com.pm.projetocomdestinoindefinido.domain.port.in.*;
import com.pm.projetocomdestinoindefinido.domain.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ControllerTest {
  @Mock NodeUseCase nodeUseCase; @Mock NodeDtoMapper nodeMapper;
  @Mock UserUseCase userUseCase; @Mock UserDtoMapper userMapper;
  @Mock ConnectionUseCase connectionUseCase; @Mock ConnectionDtoMapper connectionMapper;
  @Mock CommentUseCase commentUseCase; @Mock CommentDtoMapper commentMapper;
  @Mock JwtService jwtService; @Mock AuthenticationManager authenticationManager; @Mock Authentication authentication;

  @Test void nodeControllerDelegatesGetAndCreate() {
    Node node = new Node(); NodeResponseDTO dto = new NodeResponseDTO(); UUID id = UUID.randomUUID();
    when(nodeUseCase.getNode(id)).thenReturn(node); when(nodeMapper.toResponseDto(node)).thenReturn(dto);
    NodeController controller = new NodeController(nodeMapper, nodeUseCase);
    assertThat(controller.getNode(id.toString())).isSameAs(dto);
    NodeRequestDTO request = new NodeRequestDTO(); request.setContent("text"); when(nodeUseCase.createNode("text")).thenReturn(node);
    assertThat(controller.createNode(request)).isSameAs(dto);
  }

  @Test void userControllerCoversCreateUpdateAndMissingUpdate() {
    UserRequestDTO request = new UserRequestDTO(); request.setName("Ada"); request.setPassword("pw");
    User user = new User(); UserResponseDTO dto = new UserResponseDTO(); UUID id = UUID.randomUUID();
    when(userMapper.toModel(request)).thenReturn(user); when(userUseCase.createUser(user)).thenReturn(user); when(userMapper.toDto(user)).thenReturn(dto);
    UserController controller = new UserController(userUseCase, userMapper);
    assertThat(controller.createUser(request)).isSameAs(dto);
    when(userUseCase.updateUser(id, user)).thenReturn(user);
    assertThat(controller.updateUser(id.toString(), request)).extracting(r -> r.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    when(userUseCase.updateUser(id, user)).thenReturn(null);
    assertThat(controller.updateUser(id.toString(), request).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    controller.deleteUser(id.toString()); verify(userUseCase).deleteUser(id);
  }

  @Test void connectionAndCommentControllersMapRequests() {
    UUID start = UUID.randomUUID(), end = UUID.randomUUID(), parent = UUID.randomUUID();
    Connection connection = new Connection(); ConnectionResponseDTO connectionDto = new ConnectionResponseDTO();
    when(connectionUseCase.getNodeConnections(start)).thenReturn(List.of(connection)); when(connectionMapper.toDto(connection)).thenReturn(connectionDto);
    ConnectionController connectionController = new ConnectionController(connectionUseCase, connectionMapper);
    assertThat(connectionController.getNodeConnections(start.toString())).containsExactly(connectionDto);
    ConnectionRequestDTO connectionRequest = new ConnectionRequestDTO(); connectionRequest.setStartNodeId(start.toString()); connectionRequest.setEndNodeId(end.toString());
    when(connectionUseCase.createConnection(start, end)).thenReturn(connection); assertThat(connectionController.getNodeConnections(connectionRequest)).isSameAs(connectionDto);

    Comment comment = new Comment(); CommentResponseDTO commentDto = new CommentResponseDTO();
    when(commentUseCase.getSubComments(parent)).thenReturn(List.of(comment)); when(commentMapper.toDto(comment)).thenReturn(commentDto);
    CommentController commentController = new CommentController(commentUseCase, commentMapper);
    assertThat(commentController.getSubComments(parent.toString())).containsExactly(commentDto);
    CommentRequestDTO commentRequest = new CommentRequestDTO(); commentRequest.setContent("reply"); commentRequest.setParentCommentId(parent.toString());
    when(commentUseCase.createComment("reply", parent)).thenReturn(comment); assertThat(commentController.createComment(commentRequest)).isSameAs(commentDto);
  }

  @Test void authControllerReturnsTokenForAuthenticatedRequest() {
    AuthRequestDTO request = new AuthRequestDTO(); request.setUserId("user"); request.setPassword("pw");
    when(authenticationManager.authenticate(any())).thenReturn(authentication); when(authentication.isAuthenticated()).thenReturn(true); when(jwtService.generateToken("user")).thenReturn("token");
    assertThat(new AuthController(jwtService, authenticationManager).authenticateAndGetToken(request)).isEqualTo("token");
  }

  @Test void authControllerRejectsUnauthenticatedRequest() {
    AuthRequestDTO request = new AuthRequestDTO(); request.setUserId("user"); request.setPassword("pw");
    when(authenticationManager.authenticate(any())).thenReturn(authentication); when(authentication.isAuthenticated()).thenReturn(false);
    assertThatThrownBy(() -> new AuthController(jwtService, authenticationManager).authenticateAndGetToken(request)).isInstanceOf(UsernameNotFoundException.class);
  }
}
