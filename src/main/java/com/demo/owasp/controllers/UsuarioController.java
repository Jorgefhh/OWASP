package com.demo.owasp.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.owasp.model.Usuario;
import com.demo.owasp.services.impl.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    //@Autowired

    /*IDEA:
    Usuario inicialmente puede hacer ambas cosas : Cliente + Admin
     -> Vulnerable: Sin roles puede haber problemas 
    */
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    


    // ---------------- Endpoints con seguridad para roles ----------------- //
    
    //Ver un perfil (ADMIN: VE CUALQUIERA / CLIENTE: SOLO SU PROPIO ID)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (isAuthenticated() and #id == authentication.principal.id)")
    public ResponseEntity<Usuario> miPerfil(@PathVariable Integer id) {
        Usuario usuario = service.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    //3: Listar todos los perfiles activos (SOLO ADMIN)
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    
    
    //4 : Modificar su perfil (ADMIN O CLIENTE si es su ID)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> modificarMiPerfil(@PathVariable Integer id, @RequestBody Usuario usuario) {
        service.modificar(id, usuario);
        return ResponseEntity.ok().build();
    }

    //5 : Dar de baja cualquier usuario (SOLO ADMIN)
    @DeleteMapping("/{id}")   //Comentar esta linea para testear vulnerablidades.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darBaja(id);
        return ResponseEntity.ok().build();
    }
  
}
