package com.bafagroupe.admin.management.users;

import java.util.ArrayList;
import java.util.Arrays;

import com.bafagroupe.admin.management.users.service.UserService;
import lombok.RequiredArgsConstructor;
import com.bafagroupe.admin.management.users.model.AppUser;
import com.bafagroupe.admin.management.users.model.AppUserRole;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class AdminManagementUsersApp implements CommandLineRunner {

  final UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(AdminManagementUsersApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
    AppUser admin = new AppUser();
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setActivated(true);
    admin.setValidated(true);
    admin.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN)));

    userService.signup(admin);

    AppUser client = new AppUser();
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_USER)));

    userService.signup(client);
  }

}
