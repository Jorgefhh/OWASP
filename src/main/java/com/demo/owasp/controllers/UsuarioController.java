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

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    /*IDEA:
    Usuario inicialmente puede hacer ambas cosas : Cliente + Admin
     -> Vulnerable: Sin roles puede haber problemas 
    */


    //Acciones de Cliente
    
    @GetMapping("/{id}")
    public Usuario getUser(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }


    //Acciones de Admin


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


}
