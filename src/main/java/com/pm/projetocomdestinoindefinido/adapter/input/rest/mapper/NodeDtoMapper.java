package com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.NodeRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.NodeResponseDTO;
import com.pm.projetocomdestinoindefinido.domain.model.Node;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface NodeDtoMapper {

  @Mapping(source = "creatorUser.id", target = "creatorUserId")
  NodeResponseDTO toResponseDto (Node node);

  Node toModel (NodeRequestDTO nodeRequestDTO);
}
