package com.bafagroupe.admin.management.users.service;

import javax.servlet.http.HttpServletRequest;

import com.bafagroupe.admin.management.users.exception.CustomException;
import com.bafagroupe.admin.management.users.security.JwtTokenProvider;
import com.bafagroupe.admin.management.users.utils.MessageResponse;
import com.bafagroupe.admin.management.users.utils.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bafagroupe.admin.management.users.model.AppUser;
import com.bafagroupe.admin.management.users.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity<?> signin (String email, String password) {
    try {
      AppUser currentUser = userRepository.findByEmail(email);
      if(currentUser != null && currentUser.isActivated() && currentUser.isValidated()) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getAppUserRoles());

        return ResponseEntity.ok()
                .body(new UserInfoResponse(currentUser.getId(),
                        currentUser.getEmail(),
                        currentUser.getAppUserRoles(),token,"Login successfully"));
      }else {
        return ResponseEntity.ok()
                .body(new MessageResponse("User not activated or not validated !"));
      }
    } catch (AuthenticationException e) {
      return ResponseEntity.ok()
              .body(new MessageResponse("Invalid username or password"));
    }
  }

  public ResponseEntity<?> signup(AppUser appUser) {
    Map<Object, Object> model = new HashMap<>();
    if (!userRepository.existsByEmail(appUser.getEmail())) {
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      AppUser userCreated= userRepository.save(appUser);
      String token = jwtTokenProvider.createToken(appUser.getEmail(), appUser.getAppUserRoles());

      return ResponseEntity.ok()
              .body(new UserInfoResponse(userCreated.getId(),
                      userCreated.getEmail(),
                      userCreated.getAppUserRoles(),token,"User registered successfully!"));
    } else {
     // throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
      return ResponseEntity.ok()
              .body(new UserInfoResponse(appUser.getId(),
                      appUser.getEmail(),
                      appUser.getAppUserRoles(),null,"Username is already in use"));
    }
  }

  public void delete(String username) {
    userRepository.deleteByEmail(username);
  }

  public AppUser search(String email) {
    AppUser appUser = userRepository.findByEmail(email);
    if (appUser == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return appUser;
  }

  public AppUser whoami(HttpServletRequest req) {
    return userRepository.findByEmail(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String email) {
    return jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getAppUserRoles());
  }

}
