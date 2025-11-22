package com.demo.owasp.services.impl;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.owasp.model.CustomUserDetails;
import com.demo.owasp.model.Usuario;
import com.demo.owasp.repositories.UsuariosRepository;
import com.demo.owasp.services.interfaces.IUsuarioService;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
 
    private final IUsuarioService service;

    public UserDetailsService(IUsuarioService service){
        this.service = service;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = service.buscarPorCorreo(email);
            

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        usuario.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.name()));
        });

        CustomUserDetails user = new CustomUserDetails(usuario.getId(),
            email, 
            usuario.getClave(), 
            authorities,
            usuario.isActivo()
            );
            
        return user;
    }
    

}