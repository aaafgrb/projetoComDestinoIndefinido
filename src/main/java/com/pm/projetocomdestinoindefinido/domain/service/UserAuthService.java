package com.pm.projetocomdestinoindefinido.domain.service;

import com.pm.projetocomdestinoindefinido.domain.model.User;
import com.pm.projetocomdestinoindefinido.domain.model.UserAuthDetails;
import com.pm.projetocomdestinoindefinido.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserAuthService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public @NonNull UserDetails loadUserByUsername(@NonNull String idStr) throws UsernameNotFoundException {

    UUID id = UUID.fromString(idStr);
    User user = userRepository.getUserById(id)
      .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

    return new UserAuthDetails(user);
  }

}
