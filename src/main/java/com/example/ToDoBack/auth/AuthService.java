package com.example.ToDoBack.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ToDoBack.JWT.JwtService;
import com.example.ToDoBack.User.Role;
import com.example.ToDoBack.User.User;
import com.example.ToDoBack.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        /**
         * Registers a new user and returns the authentication response.
         *
         * @param request the register request containing user details
         * @return the authentication response containing the generated token
         */
        public AuthResponse register(RegisterRequest request) {
                User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .role(Role.USER)
                                .build();

                userRepository.insert(user);

                return AuthResponse.builder()
                                .token(jwtService.getToken(user))
                                .build();
        }

        /**
         * Logs in the user and returns an authentication response.
         *
         * @param request the login request containing the username and password
         * @return the authentication response containing the token
         */
        public AuthResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                UserDetails user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new IllegalArgumentException("Nombre de usuario no encontrado!"));
                String token = jwtService.getToken(user);
                return AuthResponse.builder()
                                .token(token)
                                .build();
        }

}
