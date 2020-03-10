package com.diego.muroMensajes.datos.generico;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.diego.muroMensajes.servicios.Autenticacion;
import com.diego.muroMensajes.sesiones.Carrito;

@Controller
public class RutasGenericas {
	
	@GetMapping("/")
	public String rutaPrincipal( ModelMap model) {
		
		
		return "index.html";
	}
	
	@GetMapping("/inicioCarrito")
	public String inicioCarrito(HttpSession sesion) {
		sesion.setAttribute("carrito", new Carrito());
		System.out.println("Se inicia el carrito dentro de la ruta de login");
		
		return "redirect:/usuarios";
		
	}
	
	
	@GetMapping("/login")
	public String seguridad(HttpSession sesion) {
		/*En nuestro caso decidimos que la sesion comienza en el momento
		  en el que un usuairo se logea, para ello colamos el atributo carrito con
		  un sistema de clave valor*/
		sesion.setAttribute("carrito", new Carrito());
		System.out.println("Se inicia el carrito dentro de la ruta de login");
		
		return "start.html";
	}
	
	@GetMapping("/addCarrito")
	public String carrito(HttpSession sesion) {
		Carrito carrito = (Carrito)sesion.getAttribute("carrito");
		if(carrito!=null) {
			carrito.addProductos();
			}
		return "redirect:/";
	}
	
	
	
	
	
	@GetMapping("/logout")
	public String algo(Autenticacion autenticacion) {
		
		return "ok";
	}
	
	
	
	

}
