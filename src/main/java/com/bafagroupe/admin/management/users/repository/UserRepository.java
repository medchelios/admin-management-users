package com.bafagroupe.admin.management.users.repository;

import javax.transaction.Transactional;

import com.bafagroupe.admin.management.users.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

  boolean existsByEmail(String email);

  AppUser findByEmail(String username);

  @Transactional
  void deleteByEmail(String email);

}
