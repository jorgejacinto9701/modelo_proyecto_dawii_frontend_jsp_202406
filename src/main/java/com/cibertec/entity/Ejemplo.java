package com.cibertec.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ejemplo {

	private int idEjemplo;
	private String descripcion;
	private Pais pais;
	private int estado;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaRegistro;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaActualizacion;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuarioRegistro;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuarioActualiza;
}
