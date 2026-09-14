package com.pm.projetocomdestinoindefinido.adapter.input.rest.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ConnectionResponseDTO {
  UUID startNodeId;
  UUID endNodeId;
  UUID creatorUserId;
}
