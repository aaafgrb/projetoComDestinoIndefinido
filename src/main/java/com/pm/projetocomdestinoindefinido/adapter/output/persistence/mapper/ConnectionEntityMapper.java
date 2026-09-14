package com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection.ConnectionEntity;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.connection.ConnectionEntityPK;
import com.pm.projetocomdestinoindefinido.domain.model.Connection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ConnectionEntityPK.class})
public interface ConnectionEntityMapper {
  ConnectionEntity toEntity(Connection connection);

  Connection toModel(ConnectionEntity connectionEntity);
}
