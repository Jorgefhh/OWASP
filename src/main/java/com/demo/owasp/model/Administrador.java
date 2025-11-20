package com.demo.owasp.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Administrador extends Usuario{

    
    //Constructor:

    public Administrador(Integer id, String nombre, String apellido, String correo, String clave ) {
        super(id, nombre, apellido, correo, clave);
    }

    
    
}
