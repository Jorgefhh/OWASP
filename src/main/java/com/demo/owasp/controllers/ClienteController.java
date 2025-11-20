package com.demo.owasp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.owasp.model.Usuario;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {



    // Un cliente puede: Ver su propio perfil.
    @GetMapping("/{id}")
    public Usuario getClient(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

}
