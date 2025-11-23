package com.demo.owasp.services.interfaces;

import java.util.List;

import com.demo.owasp.dto.UsuarioDTO;
import com.demo.owasp.model.Cliente;
import com.demo.owasp.model.Usuario;

public interface IUsuarioService {

    Usuario buscarPorId(Integer id);
    List<Usuario> listar();
    Integer registrarUsuario(Cliente usuario);
    void modificar(Integer id, Usuario usuario);
    void darBaja(Integer id);
    void activar(Integer id);
    Usuario buscarPorCorreo(String correo);
    List<UsuarioDTO> listarActivos();
}
