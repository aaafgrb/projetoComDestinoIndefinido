package com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.ConnectionRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.ConnectionResponseDTO;
import com.pm.projetocomdestinoindefinido.domain.model.Connection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConnectionDtoMapper {

  Connection toModel(ConnectionRequestDTO connectionRequestDTO);

  ConnectionResponseDTO toDto(Connection connection);
}
