package com.pm.projetocomdestinoindefinido.adapter.input.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class NodeRequestDTO {

  @NonNull
  String content;
}
