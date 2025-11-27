package com.demo.owasp.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.owasp.dto.UsuarioDTO;
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
    /*@GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (isAuthenticated() and #id == authentication.principal.id)")
    public ResponseEntity<Usuario> miPerfil(@PathVariable Integer id) {
        Usuario usuario = service.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }*/


    //2:Ven su propio perfil pero solo el DTO (USUARIOS)
    @GetMapping("/perfil/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<UsuarioDTO> miPerfilCliente(@PathVariable Integer id) {
        Usuario usuario = service.buscarPorId(id);
        UsuarioDTO dto = new UsuarioDTO(
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getCorreo()
        );
        return ResponseEntity.ok(dto);
    }

    //3: Listar todos los perfiles activos e inactivos (SOLO ADMIN)
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

    //5: (USUARIOS) Usuarios pueden listar todos los usuarios activos
    @GetMapping("/activos")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(service.listarActivos());
    }
    
    //6: Dar de baja cualquier usuario (SOLO ADMIN)
    @PatchMapping("/{id}/baja")   
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darBaja(id);
        return ResponseEntity.noContent().build();
    }
    
     //7: (INSEGURO) Dar de baja cualquier usuario 
    @PatchMapping("/{id}/baja-inseguro")
    @PreAuthorize("isAuthenticated()")   
    public ResponseEntity<Void> darDeBaja2(@PathVariable Integer id) {
        service.darBaja(id);
        return ResponseEntity.noContent().build();
    }

}
