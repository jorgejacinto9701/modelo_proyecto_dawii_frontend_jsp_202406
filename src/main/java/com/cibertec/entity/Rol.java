package com.cibertec.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rol {

	private int idRol;
	private String nombre;
	private int estado;

	
    public Rol(String nombre) {
        this.nombre = nombre;
    }
}
