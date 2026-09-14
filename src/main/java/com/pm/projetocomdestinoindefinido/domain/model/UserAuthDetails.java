package com.pm.projetocomdestinoindefinido.domain.model;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserAuthDetails implements UserDetails {

  // uses idStr as username
  private final String idStr;
  private final String password;
  private final List<GrantedAuthority> authorities;

  public UserAuthDetails(User user) {
    this.idStr = user.getId().toString();
    this.password = user.getPassword();
    this.authorities = Stream.of(user.getRoles().split(","))
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());
  }

  @Override
  public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public @Nullable String getPassword() {
    return password;
  }

  @Override
  public @NonNull String getUsername() {
    return idStr;
  }

}