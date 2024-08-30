package com.PARCINFO.demo.controller;

import com.PARCINFO.demo.dto.LoginResponse;
import com.PARCINFO.demo.dto.UserDTO;
import com.PARCINFO.demo.entity.Users;
import com.PARCINFO.demo.security.JwtTokenProvider;
import com.PARCINFO.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserManagementController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserManagementController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );
            // Recherche de l'utilisateur par email
            UserDTO user = userService.findByEmail(userDTO.getEmail());
            // Génération du token JWT en utilisant l'email comme identifiant
            String token = jwtTokenProvider.createToken(userDTO.getEmail());

            LoginResponse response = new LoginResponse(token, user);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
