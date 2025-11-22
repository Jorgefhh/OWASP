package com.demo.owasp.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    //Usuarios: Todos pueden registarse
    @PostMapping("/perfil")
    public ResponseEntity<Integer> registrar(@RequestBody Usuario usuario) {
        Integer id = service.registrarUsuario(usuario);
        return ResponseEntity.created(null).build();
    }

    //Cliente: puede ver su perfil
    @GetMapping("/perfil/{id}")
    public ResponseEntity<Usuario> miPerfil(@PathVariable Integer id) {
        Usuario usuario = service.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    //Cliente: puede modificar su perfil <- Podría ser una vulnerabilidad.
    @PutMapping("/perfil/{id}")
    public ResponseEntity<Void> modificarMiPerfil(@PathVariable Integer id, @RequestBody Usuario usuario) {
        service.modificar(id, usuario);
        return ResponseEntity.ok().build();
    }

    //Admin: puede listar todos los perfiles activos
    @GetMapping("/perfil/admin")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // Admin: modificar cualquier usuario
    @PutMapping("/admin/{id}")
    public ResponseEntity<Void> modificar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        service.modificar(id, usuario);
        return ResponseEntity.ok().build();
    }

    //Admin: dar de baja cualquier usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darBaja(id);
        return ResponseEntity.ok().build();
    }
  
}
