package com._dmicroservicios.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;

import com._dmicroservicios.demo.Dto.LoginRequest;
import com._dmicroservicios.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        
        try {
            // 1. Spring Security intentará autenticar usando el usuario en memoria
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // 2. Si las credenciales son correctas, obtenemos los detalles del usuario
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. Generamos el token usando tu clase JwtUtils
            String token = jwtUtils.generateToken(userDetails);

            // 4. Retornamos el token como un JSON
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Autenticación exitosa",
                    "token", token,
                    "username", userDetails.getUsername(),
                    "password", userDetails.getPassword()
            ));

        } catch (Exception e) {
            // Si la contraseña o el usuario son incorrectos, caerá aquí
            return ResponseEntity.status(401).body(Map.of(
                    "error", "Credenciales incorrectas"
            ));
        }
    }

    
}