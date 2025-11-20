package com.demo.owasp.model;


import java.util.List;

import com.demo.owasp.model.enums.Rol;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    //Atributos de toda la vida:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String clave;
    private String correo;

    //Rol: Puede ser Administrador o Cliente.
    @Transient
    private List<Rol> roles;


    //Constructor:

    public Usuario(Integer id, String nombre, String apellido, String correo, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo=correo;
        this.clave = clave;
    }

    //.....



}
