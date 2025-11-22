package com.demo.owasp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.demo.owasp.dto.PeticionLoginDto;
import com.demo.owasp.dto.RespuestaLoginDto;
import com.demo.owasp.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authManager;

    private JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils){
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("log-in")
    public ResponseEntity<RespuestaLoginDto> iniciarSesion(@RequestBody PeticionLoginDto peticionLogin ){
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(peticionLogin.email(), peticionLogin.password())
        );

        String email = ((UserDetails)authentication.getPrincipal()).getUsername();
        String token = jwtUtils.crearToken(authentication);

        return ResponseEntity.ok(new RespuestaLoginDto(email,token));
    }
}
