package com.example.ToDoBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.ToDoBack.JWT.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final AuthenticationProvider authProvider;
        private final JwtAuthenticationFilter JwtAuthenticationFilter;

        /**
         * Configures the security filter chain for handling HTTP requests.
         *
         * @param http the HttpSecurity object used for configuring security settings
         * @return the configured SecurityFilterChain object
         * @throws Exception if an error occurs during configuration
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf
                                                .disable())
                                .authorizeHttpRequests(authRequest -> authRequest
                                                .requestMatchers("/auth/**").permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(
                                                sessionManager -> sessionManager
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authProvider)
                                .addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
}
