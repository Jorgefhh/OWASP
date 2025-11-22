package com.demo.owasp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.owasp.model.Usuario;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuario, Integer>{

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM usuario u WHERE u.id =:id) THEN 1 ELSE 0 END",
        nativeQuery = true
    )
    Integer existe(Integer id);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM administrador a WHERE a.id =:id) THEN 1 ELSE 0 END",
        nativeQuery = true
    )
    Integer esAdministrador(Integer id);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM cliente c WHERE c.id =:id) THEN 1 ELSE 0 END",
        nativeQuery=true
    )
    Integer esCliente(Integer id);

    Optional<Usuario> findByCorreo(String correo);
}
