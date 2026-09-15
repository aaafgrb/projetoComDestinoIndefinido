package com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.CommentRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.CommentResponseDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.UserResponseDTO;
import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserResponseDTO.class})
public interface CommentDtoMapper {
  CommentResponseDTO toDto(Comment comment);

  Comment toModel(CommentRequestDTO commentRequestDTO);
}
