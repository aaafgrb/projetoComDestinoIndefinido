package com.pm.projetocomdestinoindefinido.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
  UUID id;
  String name;
}
