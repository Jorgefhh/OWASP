package com.demo.owasp.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.core.Authentication;

import com.demo.owasp.dto.PeticionLoginDto;
import com.demo.owasp.dto.RespuestaLoginDto;
import com.demo.owasp.model.Cliente;
import com.demo.owasp.services.impl.UsuarioService;
import com.demo.owasp.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    private final UsuarioService service;

    private JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils, PasswordEncoder encoder, UsuarioService service){
        this.authManager = authManager;
        this.encoder = encoder;
        this.service = service;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/log-in")
    public ResponseEntity<RespuestaLoginDto> iniciarSesion(@RequestBody PeticionLoginDto peticionLogin ){
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(peticionLogin.correo(), peticionLogin.clave())
        );

        String email = ((UserDetails)authentication.getPrincipal()).getUsername();
        String token = jwtUtils.crearToken(authentication);

        return ResponseEntity.ok(new RespuestaLoginDto(email,token));
    }

        //1: Todos pueden registarse (USUARIOS)
    @PostMapping("/register")
    public ResponseEntity<Integer> registrar(@RequestBody Cliente usuario) {
        usuario.setClave(encoder.encode(usuario.getClave()));
        Integer id = service.registrarUsuario(usuario);
        URI uri = UriComponentsBuilder.fromUriString("/usuarios/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }
}
