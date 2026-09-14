package com.pm.projetocomdestinoindefinido.adapter.input.rest.controller;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.UserRequestDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.UserResponseDTO;
import com.pm.projetocomdestinoindefinido.adapter.input.rest.mapper.UserDtoMapper;
import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.port.in.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "${projetoComDestinoIndefinido.frontendUrl}", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserUseCase userUseCase;
  private final UserDtoMapper userDtoMapper;

  @GetMapping("/{id}")
  UserResponseDTO getUser(@PathVariable String id) {
    return userDtoMapper.toDto(userUseCase.getUserById(UUID.fromString(id)));
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {

    return userDtoMapper.toDto(userUseCase.createUser(userDtoMapper.toModel(userRequestDTO)));
  }

  @PutMapping("/{id}")
  ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @RequestBody UserRequestDTO userRequestDTO) {
    User updatedUser = userUseCase.updateUser(UUID.fromString(id), userDtoMapper.toModel(userRequestDTO));

    if(updatedUser == null) return ResponseEntity.noContent().build();

    return ResponseEntity.accepted().body(userDtoMapper.toDto(updatedUser));
  }

  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable String id) {
    userUseCase.deleteUser(UUID.fromString(id));
  }
}
