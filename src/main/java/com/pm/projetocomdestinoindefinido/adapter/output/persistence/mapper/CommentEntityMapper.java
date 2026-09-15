package com.pm.projetocomdestinoindefinido.adapter.output.persistence.mapper;

import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.CommentEntity;
import com.pm.projetocomdestinoindefinido.domain.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserEntityMapper.class})
public interface CommentEntityMapper {

  Comment toModel(CommentEntity commentEntity);

  CommentEntity toEntity(Comment comment);
}
