package com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.*;
import com.pm.projetocomdestinoindefinido.domain.model.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class MapperTest {
  private final UserDtoMapper users = Mappers.getMapper(UserDtoMapper.class);
  private final ConnectionDtoMapper connections = Mappers.getMapper(ConnectionDtoMapper.class);
  private final CommentDtoMapper comments = Mappers.getMapper(CommentDtoMapper.class);
  private final NodeDtoMapper nodes = Mappers.getMapper(NodeDtoMapper.class);

  @Test void userMapperMapsRequestAndHidesPasswordInResponse() {
    UserRequestDTO request = new UserRequestDTO(); request.setName("Ada"); request.setPassword("secret");
    User user = users.toModel(request); user.setId(UUID.randomUUID());
    UserResponseDTO response = users.toDto(user);
    assertThat(user.getName()).isEqualTo("Ada"); assertThat(user.getPassword()).isEqualTo("secret");
    assertThat(response.getId()).isEqualTo(user.getId()); assertThat(response.getName()).isEqualTo("Ada");
  }

  @Test void connectionMapperMapsAllIds() {
    UUID start = UUID.randomUUID(), end = UUID.randomUUID(), creator = UUID.randomUUID();
    Connection model = new Connection(); model.setStartNodeId(start); model.setEndNodeId(end); model.setCreatorUserId(creator);
    ConnectionResponseDTO dto = connections.toDto(model);
    assertThat(dto.getStartNodeId()).isEqualTo(start); assertThat(dto.getEndNodeId()).isEqualTo(end); assertThat(dto.getCreatorUserId()).isEqualTo(creator);
  }

  @Test void commentMapperMapsNestedCreator() {
    User user = new User(); user.setId(UUID.randomUUID()); user.setName("Ada");
    Comment comment = new Comment(); comment.setId(UUID.randomUUID()); comment.setParentCommentId(UUID.randomUUID()); comment.setContent("hello"); comment.setCreatorUser(user);
    CommentResponseDTO dto = comments.toDto(comment);
    assertThat(dto.getId()).isEqualTo(comment.getId()); assertThat(dto.getParentCommentId()).isEqualTo(comment.getParentCommentId());
    assertThat(dto.getCreatorUser().getId()).isEqualTo(user.getId()); assertThat(dto.getCreatorUser().getName()).isEqualTo("Ada");
  }

  @Test void nodeMapperMapsCreatorIdAndComment() {
    User user = new User(); user.setId(UUID.randomUUID()); Comment comment = new Comment(); comment.setContent("node");
    Node node = new Node(); node.setId(UUID.randomUUID()); node.setCreatorUser(user); node.setComment(comment);
    NodeResponseDTO dto = nodes.toResponseDto(node);
    assertThat(dto.getId()).isEqualTo(node.getId()); assertThat(dto.getCreatorUserId()).isEqualTo(user.getId());
    assertThat(dto.getComment().getContent()).isEqualTo("node");
  }
}
