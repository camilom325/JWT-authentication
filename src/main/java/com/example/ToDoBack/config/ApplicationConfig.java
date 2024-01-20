package com.example.ToDoBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ToDoBack.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Returns the AuthenticationManager instance configured with the provided
     * AuthenticationConfiguration.
     *
     * @param config the AuthenticationConfiguration to configure the
     *               AuthenticationManager
     * @return the configured AuthenticationManager instance
     * @throws Exception if an error occurs while configuring the
     *                   AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Creates an instance of AuthenticationProvider.
     * 
     * @return the created AuthenticationProvider instance.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(setUserDetailsService());
        authenticationProvider.setPasswordEncoder(setPasswordEncoder());
        return authenticationProvider;
    }

    /**
     * Returns a new instance of PasswordEncoder.
     * 
     * @return the PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder setPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Returns an implementation of the UserDetailsService interface.
     * This implementation retrieves user details from the userRepository based on
     * the provided username.
     * If the username is not found, it throws an IllegalArgumentException.
     *
     * @return an implementation of the UserDetailsService interface
     */
    @Bean
    public UserDetailsService setUserDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Nombre de usuario no encontrado!"));
    }

}
