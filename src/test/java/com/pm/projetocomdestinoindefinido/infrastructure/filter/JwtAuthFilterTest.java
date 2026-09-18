package com.pm.projetocomdestinoindefinido.infrastructure.filter;

import com.pm.projetocomdestinoindefinido.domain.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthFilterTest {
  @Mock UserDetailsService userDetailsService; @Mock JwtService jwtService;
  @Mock HttpServletRequest request; @Mock HttpServletResponse response; @Mock FilterChain chain;

  @AfterEach void clearContext() { SecurityContextHolder.clearContext(); }

  @Test void bearerTokenAuthenticatesRequestWhenValid() throws Exception {
    JwtAuthFilter filter = new JwtAuthFilter(userDetailsService, jwtService);
    var details = User.withUsername("user-1").password("p").roles("USER").build();
    when(request.getHeader("Authorization")).thenReturn("Bearer token");
    when(jwtService.extractUsername("token")).thenReturn("user-1");
    when(userDetailsService.loadUserByUsername("user-1")).thenReturn(details);
    when(jwtService.validateToken("token", details)).thenReturn(true);
    filter.doFilter(request, response, chain);
    assertThat(SecurityContextHolder.getContext().getAuthentication()).isInstanceOf(UsernamePasswordAuthenticationToken.class);
    assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("user-1");
    verify(chain).doFilter(request, response);
  }

  @Test void missingBearerHeaderLeavesContextUnauthenticated() throws Exception {
    JwtAuthFilter filter = new JwtAuthFilter(userDetailsService, jwtService);
    when(request.getHeader("Authorization")).thenReturn(null);
    filter.doFilter(request, response, chain);
    assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    verifyNoInteractions(jwtService, userDetailsService);
    verify(chain).doFilter(request, response);
  }

  @Test void existingAuthenticationIsNotReplaced() throws Exception {
    JwtAuthFilter filter = new JwtAuthFilter(userDetailsService, jwtService);
    var existing = new UsernamePasswordAuthenticationToken("existing", null);
    SecurityContextHolder.getContext().setAuthentication(existing);
    when(request.getHeader("Authorization")).thenReturn("Bearer token");
    when(jwtService.extractUsername("token")).thenReturn("user-1");
    filter.doFilter(request, response, chain);
    assertThat(SecurityContextHolder.getContext().getAuthentication()).isSameAs(existing);
    verifyNoInteractions(userDetailsService);
  }
}
