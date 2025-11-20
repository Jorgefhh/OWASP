package com.demo.owasp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.owasp.model.Usuario;
import com.demo.owasp.services.impl.UsuarioService;

@RestController
@RequestMapping("/admin")  //Todas las peticiones http que tengan la ruta usuarios se manejan aquí.
public class AdminController {



    //Ejemplo CRUD
    @Autowired
    UsuarioService service;
/* 

    @DeleteMapping("/borrar/{id}")
    public String borrarUsuario(@PathVariable Integer id) {
        service.borrarPorId(id);
        return "Usuario eliminado";
    }

    @GetMapping("/listar")
    public Map<Integer, Usuario> listar() {
        return service.listar();
    }

    @PostMapping("/crear")
    public Usuario crear(@RequestBody Usuario u) {
        return service.actualizar(u.getId(), u);
    }
*/
}
