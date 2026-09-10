package com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tb_user")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  UUID id;

  @NotNull
  String name;
}
