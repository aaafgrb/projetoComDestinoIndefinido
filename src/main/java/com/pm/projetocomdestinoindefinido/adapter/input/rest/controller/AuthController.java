package com.pm.projetocomdestinoindefinido.adapter.input.rest.controller;

import com.pm.projetocomdestinoindefinido.adapter.input.rest.dto.AuthRequestDTO;
import com.pm.projetocomdestinoindefinido.domain.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${projetoComDestinoIndefinido.frontendUrl}", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/generateToken")
  public String authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDto) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequestDto.getUserId(), authRequestDto.getPassword())
    );
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(authRequestDto.getUserId());
    } else {
      throw new UsernameNotFoundException("Invalid user request!");
    }
  }
}
