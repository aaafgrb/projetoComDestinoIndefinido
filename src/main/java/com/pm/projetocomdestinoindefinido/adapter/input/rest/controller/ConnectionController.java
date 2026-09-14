package com.pm.projetocomdestinoindefinido.adapter.input.rest.controller;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.ConnectionRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.ConnectionResponseDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper.ConnectionDtoMapper;
import com.pm.projetocomdestinoindefinido.domain.model.Connection;
import com.pm.projetocomdestinoindefinido.domain.port.in.ConnectionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "${projetoComDestinoIndefinido.frontendUrl}", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/connection")
public class ConnectionController {

  private final ConnectionUseCase connectionUseCase;
  private final ConnectionDtoMapper connectionDtoMapper;

  @GetMapping("/{startNodeId}")
  List<ConnectionResponseDTO> getNodeConnections(@PathVariable String startNodeId) {
    return connectionUseCase.getNodeConnections(UUID.fromString(startNodeId)).stream().map(connectionDtoMapper::toDto).toList();
  }

  @PostMapping("/")
  ConnectionResponseDTO getNodeConnections(@RequestBody ConnectionRequestDTO connectionRequestDTO) {
    Connection connection = connectionUseCase.createConnection(
            UUID.fromString(connectionRequestDTO.getStartNodeId()),
            UUID.fromString(connectionRequestDTO.getEndNodeId()));

    return connectionDtoMapper.toDto(connection);
  }
}
