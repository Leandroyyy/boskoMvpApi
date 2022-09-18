package br.com.bosko.boskoapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
        .and()
          .authorizeHttpRequests()
          .antMatchers("/h2-console/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
          .antMatchers(HttpMethod.POST, "/api/user/").permitAll()
          .antMatchers(HttpMethod.PUT, "/api/user/**").authenticated()
          .antMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()
          .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
          .antMatchers(HttpMethod.POST, "/api/book/").permitAll()
          .antMatchers(HttpMethod.PUT, "/api/book/**").authenticated()
          .antMatchers(HttpMethod.DELETE, "/api/book/**").authenticated()
          .antMatchers(HttpMethod.GET, "/api/user/progress/**").authenticated()
          .antMatchers(HttpMethod.POST, "/api/user/progress/").authenticated()
          .antMatchers(HttpMethod.PUT, "/api/user/progress/**").authenticated()
          .antMatchers(HttpMethod.DELETE, "/api/user/progress/**").authenticated()
          .anyRequest().denyAll()
        .and()
          .headers().frameOptions().disable()
        .and()
          .csrf().disable();

        return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
