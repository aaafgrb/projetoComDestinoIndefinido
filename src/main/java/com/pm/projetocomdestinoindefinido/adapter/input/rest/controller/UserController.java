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

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "${frontendUrl}")
public class UserController {

  private final UserUseCase userUseCase;

  @GetMapping("/{id}")
  UserResponseDTO getUser(@PathVariable String id) {
    return UserDtoMapper.toDto(userUseCase.getUserById(UUID.fromString(id)));
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
    return UserDtoMapper.toDto(userUseCase.createUser(UserDtoMapper.toModel(userRequestDTO)));
  }

  @PutMapping("/{id}")
  ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id, @RequestBody UserRequestDTO userRequestDTO) {
    User updatedUser = userUseCase.updateUser(UUID.fromString(id), UserDtoMapper.toModel(userRequestDTO));

    if(updatedUser == null) return ResponseEntity.noContent().build();

    return ResponseEntity.accepted().body(UserDtoMapper.toDto(updatedUser));
  }

  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable String id) {
    userUseCase.deleteUser(UUID.fromString(id));
  }
}
