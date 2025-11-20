package com.demo.owasp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Cliente extends Usuario{

    //Atributos:

    private Integer puntos;

    public Cliente(Integer id, String nombre, String apellido, String correo, String clave, Integer puntos){
        super(id, apellido, nombre, correo, clave);
        this.puntos = puntos;
    }


    //....
}
