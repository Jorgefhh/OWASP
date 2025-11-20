package com.demo.owasp.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Administrador extends Usuario{

    
    //Constructor:

    public Administrador(Integer id, String nombre, String apellido, String correo, String clave ) {
        super(id, nombre, apellido, correo, clave);
    }

    
    
}
