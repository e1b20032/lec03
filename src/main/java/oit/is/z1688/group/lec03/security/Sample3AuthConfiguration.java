package oit.is.z1688.group.lec03.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class Sample3AuthConfiguration {

  /**
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserBuilder users = User.builder();

    UserDetails user1 = users
        .username("user1")
        .password("$2y$10$yYFP8zFLCH.kv5Y1eml5GeRi.6SQjw8IaDOuIUWU91Y49O41Yp/6e")
        .roles("USER")
        .build();

    UserDetails admin = users
        .username("admin")
        .password("$2y$10$GmYdlR7XWgcZH.j2VQdVW.36jB3HKXDn2ljNE7yQ7jjjyHxR4Vdzi")
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user1, admin);
  }

  /**
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin();
    http.authorizeHttpRequests()
        .mvcMatchers("/sample3/**").authenticated();

    http.logout().logoutSuccessUrl("/");
    return http.build();
  }

  /**
   * @return
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
