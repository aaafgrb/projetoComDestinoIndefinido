//package com.pm.projetocomdestinoindefinido.domain.service;
//
//import com.pm.projetocomdestinoindefinido.domain.model.Node;
//import com.pm.projetocomdestinoindefinido.domain.port.in.NodeUseCase;
//import com.pm.projetocomdestinoindefinido.adapter.output.persistence.entity.NodeEntity;
//import com.pm.projetocomdestinoindefinido.domain.port.out.NodeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class NodeService implements NodeUseCase {
//
//  private final NodeRepository nodeRepository;
//
//  Node getNode(UUID id) {
//    NodeEntity nodeEntity = nodeRepository.getReferenceById(id);
//  }
//}
