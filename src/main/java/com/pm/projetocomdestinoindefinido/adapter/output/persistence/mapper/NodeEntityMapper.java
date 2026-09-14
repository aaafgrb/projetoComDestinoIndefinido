package com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper;

import com.pm.projetocomdestinoindefinido.domain.model.Node;
import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.NodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserEntityMapper.class })
public interface NodeEntityMapper {

  @Mapping(source = "creatorUser", target = "creatorUserEntity")
  NodeEntity toEntity (Node node);

  @Mapping(source = "creatorUserEntity", target = "creatorUser")
  Node toModel(NodeEntity nodeEntity);
}
