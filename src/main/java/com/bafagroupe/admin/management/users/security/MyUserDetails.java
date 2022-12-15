package com.bafagroupe.admin.management.users.security;

import lombok.RequiredArgsConstructor;
import com.bafagroupe.admin.management.users.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bafagroupe.admin.management.users.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final AppUser appUser = userRepository.findByEmail(email);

    if (appUser == null) {
      throw new UsernameNotFoundException("User '" + email + "' not found");
    }

    return org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(appUser.getPassword())
        .authorities(appUser.getAppUserRoles())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }

}
