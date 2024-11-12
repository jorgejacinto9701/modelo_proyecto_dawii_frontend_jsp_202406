package com.cibertec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cibertec.entity.Ejemplo;
import com.cibertec.entity.Usuario;
import com.cibertec.util.AppSettings;

import jakarta.servlet.http.HttpSession;


@Controller
public class EjemploRegistroController {

    @Autowired
    private RestTemplate restTemplate;
    
	@PostMapping("/registraEjemplo")
	@ResponseBody
	public Map<?, ?> registra(Ejemplo obj, HttpSession session){
		Usuario objUsuario = (Usuario)session.getAttribute("objUsuario");
		obj.setUsuarioActualiza(objUsuario);
		obj.setUsuarioRegistro(objUsuario);
		HashMap<String, String> map = new HashMap<String, String>();
		Ejemplo objSalida = restTemplate.postForObject(AppSettings.URL_BACKEND +"/url/ejemplo/registraEjemplo", obj, Ejemplo.class);
		if (objSalida == null) {
			map.put("MENSAJE", "Error en el registro");
		}else {
			map.put("MENSAJE", "Registro exitoso");
		}
		return map;
	}
}
