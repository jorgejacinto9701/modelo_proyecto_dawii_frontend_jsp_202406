package com.cibertec.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Usuario {

	private int idUsuario;
	private String nombres;
	private String apellidos;
	private String dni;
	private String login;
	private String password;
	private String correo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
	private Date fechaNacimiento;
	
	private String direccion;
	
	private int idUsuarioSuperior;
	private int estado;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaRegistro;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaActualizacion;
	
	private int idUsuarioRegistro;
	
	private int idUsuarioActualiza;
	
	
	public Usuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNombreCompleto() {
		if (nombres != null && apellidos != null) {
			return apellidos.concat(" ").concat(nombres);	
		}else {
			return ""; 
		}
	}
}
