package com.cibertec.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authority {

    @JsonProperty("authority")
    private String authority;

    
}