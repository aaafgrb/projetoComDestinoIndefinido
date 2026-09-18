package com.pm.projetocomdestinoindefinido.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class JwtServiceTest {
  private JwtService service;

  @BeforeEach void setUp() throws Exception {
    service = new JwtService();
    Field secret = JwtService.class.getDeclaredField("SECRET");
    secret.setAccessible(true);
    secret.set(service, Base64.getEncoder().encodeToString("01234567890123456789012345678901".getBytes()));
  }

  @Test void tokenContainsSubjectAndIsValidForMatchingUser() {
    String token = service.generateToken("user-1");
    var details = User.withUsername("user-1").password("p").roles("USER").build();
    assertThat(service.extractUsername(token)).isEqualTo("user-1");
    assertThat(service.extractExpiration(token)).isAfter(new Date());
    assertThat(service.validateToken(token, details)).isTrue();
  }

  @Test void tokenIsInvalidForAnotherUser() {
    String token = service.generateToken("user-1");
    var details = User.withUsername("user-2").password("p").roles("USER").build();
    assertThat(service.validateToken(token, details)).isFalse();
  }
}
