package com.demo.owasp.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Cliente extends Usuario{

    //Atributos:

    private Integer puntos;

    public Cliente(Integer id, String nombre, String apellido, String correo, String clave, Integer puntos, boolean activo){
        super(id, apellido, nombre, correo, clave, activo);
        this.puntos = puntos;
    }


    //....
}
