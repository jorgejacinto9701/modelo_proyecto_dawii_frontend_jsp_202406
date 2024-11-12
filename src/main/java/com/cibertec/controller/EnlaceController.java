package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EnlaceController {

	//Login
	@GetMapping("/")
	public String verLogin() {	return "intranetLogin";  }
	
	@GetMapping("/verIntranetHome")
	public String verIntranetHome() {	return "intranetHome";  }

	//Registro

	@GetMapping("/verRegistroEjemplo")
	public String verRegistroEjemplo() {	return "intranetRegistroEjemplo";	}

}
