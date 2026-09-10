package com.pm.projetocomdestinoindefinido.infrastructure.persistence;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
}
