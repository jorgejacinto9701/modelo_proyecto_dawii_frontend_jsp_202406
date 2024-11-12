package com.cibertec.security;

import java.util.List;

import com.cibertec.entity.Opcion;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {


    @JsonProperty("token")
    private String token;

    @JsonProperty("type")
    private String type;

    @JsonProperty("login")
    private String login;

    @JsonProperty("idUsuario")
    private int idUsuario;
    
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;

    @JsonProperty("authorities")
    private List<Authority> authorities;

    @JsonProperty("opciones")
    private List<Opcion> opciones;

   
}