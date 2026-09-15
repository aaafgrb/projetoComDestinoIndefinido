package com.pm.projetocomdestinoindefinido.adapter.input.rest.controller;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.NodeRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.NodeResponseDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper.NodeDtoMapper;
import com.pm.projetocomdestinoindefinido.domain.port.in.NodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/node")
public class NodeController {

  private final NodeDtoMapper nodeDtoMapper;
  private final NodeUseCase nodeUseCase;

  @GetMapping("/{id}")
  NodeResponseDTO getNode(@PathVariable String id) {
    return nodeDtoMapper.toResponseDto(nodeUseCase.getNode(UUID.fromString(id)));
  }

  @PostMapping("/")
  NodeResponseDTO createNode(@RequestBody NodeRequestDTO nodeRequestDTO) {
    return nodeDtoMapper.toResponseDto(nodeUseCase.createNode(nodeRequestDTO.getContent()));
  }
}
