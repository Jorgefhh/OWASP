package com.demo.owasp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.demo.owasp.dto.UsuarioDTO;
import com.demo.owasp.model.Cliente;
import com.demo.owasp.model.Usuario;
import com.demo.owasp.model.enums.Rol;
import com.demo.owasp.repositories.UsuariosRepository;
import com.demo.owasp.services.interfaces.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{


    private final UsuariosRepository repo;

    public UsuarioService(UsuariosRepository repo){
        this.repo = repo;
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        //Obtiene el usuario y si no existe lanza una excepción
        Usuario usuario = repo.findById(id).orElseThrow();
        /*if (!usuario.isActivo()) {  //Debería dejar buscar usuarios activos?
            throw new RuntimeException();
        }*/
        usuario.setRoles(obtenerRoles(usuario.getId()));

        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = StreamSupport.stream(repo.findAll().spliterator(), false)
                            .collect(Collectors.toList());
        
        lista.forEach(usuario->{usuario.setRoles(obtenerRoles(usuario.getId()));});

        return lista;
    }

    public List<UsuarioDTO> listarActivos(){
        List<Usuario> lista = repo.listarActivos();
                
        List<UsuarioDTO> listaDto = new ArrayList<>();

        lista.forEach(u -> {
            listaDto.add(
                new UsuarioDTO(u.getNombre(),u.getApellido(),u.getCorreo())
            );
        });
        
        return listaDto;
    }

    /**
     * Registra un nuevo usuario con el Rol CLIENTE y 0 puntos.
     */
    @Override
    public Integer registrarUsuario(Cliente cliente) {
        cliente.setPuntos(0);
        cliente.setActivo(true);
        Cliente nuevo = repo.save(cliente);
        return nuevo.getId();
    }

    @Override
    public void modificar(Integer id, Usuario usuario) {
        Usuario u = repo.findById(id).orElseThrow();
        usuario.setCorreo(u.getCorreo());
        repo.save(usuario);
    }

    private List<Rol> obtenerRoles(Integer id){
        List<Rol> roles = new ArrayList<>();

        if (repo.esAdministrador(id)==1) {
            roles.add(Rol.ROLE_ADMIN);
        }

        if (repo.esCliente(id)==1) {
            roles.add(Rol.ROLE_CLIENTE);
        }

        return roles;
    }

    @Override
    public void darBaja(Integer id) {
        Usuario u = repo.findById(id).orElseThrow();
        u.setActivo(false);
        repo.save(u);
    }

    @Override
    public void activar(Integer id) {
        Usuario u = repo.findById(id).orElseThrow();
        u.setActivo(true);
        repo.save(u);
    }

    @Override
    public Usuario buscarPorCorreo(String correo){
        Usuario u = repo.findByCorreo(correo).orElseThrow();
        u.setRoles(obtenerRoles(u.getId()));
        return u;
    }

}
