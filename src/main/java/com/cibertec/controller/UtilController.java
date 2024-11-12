package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cibertec.entity.Pais;
import com.cibertec.util.AppSettings;
@Controller
public class UtilController {

    @Autowired
    private RestTemplate restTemplate;

	@GetMapping("/listaPais")
	@ResponseBody
	public List<Pais> listaPais() {
		List<Pais> lista = restTemplate.exchange(AppSettings.URL_BACKEND +"/url/util/listaPais",HttpMethod.GET,null,new ParameterizedTypeReference<List<Pais>>(){}).getBody();
		return lista;	
	}
	
	
}
