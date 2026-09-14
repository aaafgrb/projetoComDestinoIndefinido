package com.pm.projetocomdestinoindefinido.adapter.input.rest.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class AuthRequestDto {
  @NonNull
  String userId;

  @NonNull
  String password;
}
