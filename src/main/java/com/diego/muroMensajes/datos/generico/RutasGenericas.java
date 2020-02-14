package com.diego.muroMensajes.datos.generico;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.diego.muroMensajes.servicios.Autenticacion;
import com.diego.muroMensajes.sesiones.Carrito;

@Controller
public class RutasGenericas {
	
	@GetMapping("/")
	public String rutaPrincipal() {
		
	
		return "index.html";
	}
	
	
	@GetMapping("/login")
	public String seguridad(HttpSession sesion) {
		sesion.setAttribute("carrito", new Carrito());
		
		return "menulogin";
	}
	
	@GetMapping("/logout")
	public String algo(Autenticacion autenticacion) {
		
		return "ok";
	}
	
	
	@GetMapping("/addCarrito")
	public String algo(Autenticacion autenticacion) {
		
		return "ok";
	}

}
