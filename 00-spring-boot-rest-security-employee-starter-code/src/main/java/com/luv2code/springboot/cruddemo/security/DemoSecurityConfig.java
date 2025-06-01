package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity // Add to a config class

@Configuration
public class DemoSecurityConfig {

    // ✅ 1. In-memory users
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails naveen = User.builder()
                .username("Naveen")
                .password("{noop}n123")
                .roles("ADMIN","MANAGER")
                .build();

        UserDetails sweety = User.builder()
                .username("Sweety")
                .password("{noop}s123")
                .roles("EMPLOYEE")
                .build();

        UserDetails sachin = User.builder()
                .username("Sachin")
                .password("{noop}sa123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mehak = User.builder()
                .username("Mehak")
                .password("{noop}m123")
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(naveen, sweety, sachin, mehak);
    }

    // ✅ 2. Security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // or .formLogin() if using form login
                .csrf(csrf -> csrf.disable()); // disable CSRF for API testing (Postman etc.)

        return http.build();
    }
}


