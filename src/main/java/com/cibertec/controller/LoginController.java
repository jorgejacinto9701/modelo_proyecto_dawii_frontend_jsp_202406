package com.cibertec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cibertec.entity.Opcion;
import com.cibertec.entity.Rol;
import com.cibertec.entity.Usuario;
import com.cibertec.security.JwtDto;
import com.cibertec.security.LoginUsuario;
import com.cibertec.util.AppSettings;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/login")
	public String login(Usuario user, HttpSession session) {
		LoginUsuario objLogin = new LoginUsuario();
		objLogin.setLogin(user.getLogin());
		objLogin.setPassword(user.getPassword());
		String AUTH_URL = AppSettings.URL_BACKEND + "/url/auth/login";

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<LoginUsuario> request = new HttpEntity<>(objLogin, headers);

		try {
			JwtDto objSalida = restTemplate.postForObject(AUTH_URL, request, JwtDto.class);
			if (objSalida != null) {
				List<Rol> roles = new ArrayList<>();
				objSalida.getAuthorities().forEach(authority -> roles.add(new Rol(authority.getAuthority())));

				List<Opcion> menus = objSalida.getOpciones();
				List<Opcion> menusTipo1 = menus.stream().filter(p -> p.getTipo() == 1).toList();
				List<Opcion> menusTipo2 = menus.stream().filter(p -> p.getTipo() == 2).toList();
				List<Opcion> menusTipo3 = menus.stream().filter(p -> p.getTipo() == 3).toList();

				session.setAttribute("objUsuario", new Usuario(objSalida.getIdUsuario()));
				session.setAttribute("objMenusTipo1", menusTipo1);
				session.setAttribute("objMenusTipo2", menusTipo2);
				session.setAttribute("objMenusTipo3", menusTipo3);
				session.setAttribute("objRoles", roles);
				session.setAttribute("token", objSalida.getToken());

				return "intranetHome";
			} else {
				// Maneja el caso en que la respuesta sea nula
				return "intranetLogin";
			}
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				// Maneja el error 401 Unauthorized
				System.out.println("Unauthorized: Invalid credentials");
			} else {
				e.printStackTrace();
			}
			return "intranetLogin";
		} catch (Exception e) {
			e.printStackTrace();
			return "intranetHome";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();

		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "no-cache");

		request.setAttribute("mensaje", "El usuario salió de sesión");
		return "intranetLogin";

	}

}
