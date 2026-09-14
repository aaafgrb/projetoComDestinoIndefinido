package com.pm.projetocomdestinoindefinido.adapter.input.rest.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthRequestDTO {
  @NonNull
  String userId;

  @NonNull
  String password;
}
