package com._dmicroservicios.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;



@RestController
@RequestMapping("/v0/auth")
public class AuthController {
    

    private final AuthenticationManager authenticationManager;

  

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
      
    }


}
