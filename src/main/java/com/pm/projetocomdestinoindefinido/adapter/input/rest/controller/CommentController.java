package com.pm.projetocomdestinoindefinido.adapter.input.rest.controller;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.CommentRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.CommentResponseDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.NodeRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.NodeResponseDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper.CommentDtoMapper;
import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import com.pm.projetocomdestinoindefinido.domain.port.in.CommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

  private final CommentUseCase commentUseCase;
  private final CommentDtoMapper commentDtoMapper;

  @GetMapping("/{parentCommentId}")
  List<CommentResponseDTO> getSubComments(@PathVariable String parentCommentId) {
    return commentUseCase.getSubComments(UUID.fromString(parentCommentId)).stream().map(commentDtoMapper::toDto).toList();
  }

  @PostMapping("/")
  CommentResponseDTO createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
    return commentDtoMapper.toDto(commentUseCase.createComment(
            commentRequestDTO.getContent(),
            UUID.fromString(commentRequestDTO.getParentCommentId())));
  }
}
