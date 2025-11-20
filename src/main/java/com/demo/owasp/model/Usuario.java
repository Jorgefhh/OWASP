package com.demo.owasp.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Usuario {
    //Atributos de toda la vida:
    private Integer id;
    private String nombre;
    private String apellido;
    private String clave;
    private String correo;

    //Rol: Puede ser Administrador o Cliente.
    private String rol; //Podría ser un enum ?


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
