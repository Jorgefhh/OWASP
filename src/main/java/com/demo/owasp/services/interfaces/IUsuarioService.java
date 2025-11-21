package com.demo.owasp.services.interfaces;

import java.util.List;

import com.demo.owasp.model.Usuario;

public interface IUsuarioService {

    Usuario buscarPorId(Integer id);
    List<Usuario> listar();
    Integer registrarUsuario(Usuario usuario);
    void modificar(Integer id, Usuario usuario);
    void darBaja(Integer id);
    void activar(Integer id);
}
