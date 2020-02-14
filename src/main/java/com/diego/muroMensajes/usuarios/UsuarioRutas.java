package com.diego.muroMensajes.usuarios;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.jboss.jandex.TypeTarget.Usage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.diego.muroMensajes.beans.GeneradorCript;
import com.diego.muroMensajes.datos.rol.Rol;
import com.diego.muroMensajes.datos.rol.RolDAO;
import com.diego.muroMensajes.servicios.Autenticacion;
import com.diego.muroMensajes.sesiones.Carrito;

@Controller
public class UsuarioRutas {
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private GeneradorCript generadorCript;
	
	@Autowired
	private RolDAO rolDAO;
	
	
	@GetMapping("/usuarios")
	public ModelAndView todosLosUsuarios(HttpSession sesion) {
		/*Codigo del carrito*/
//		Carrito carrito = (Carrito)sesion.getAttribute("carrito");
//		if(carrito != null) {
//		carrito.setContenido("Tengo 1 producto");
//		}
		/*Ruta normal de usuario*/
		ModelAndView nav = new ModelAndView();
		
		nav.setViewName("usuarios");
		nav.addObject("usuario", new Usuario());
		
		List<Usuario> listaUsuarios = (List<Usuario>)usuarioDAO.findAll();
		nav.addObject("usuarios", listaUsuarios);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		nav.addObject("roles", listaRoles);
		
		return nav;
		
		
		
	}
	
	@PostMapping("/usuarios/anadir")
	public String crearUsuario(@ModelAttribute Usuario usuario) {
		/*Antes de guardarlo creo un objeto del tipo que yo voy a utilizar 
		 * para encriptar.
		 * Establece de contraseña eñ resultado de codificar el getter de la pass que te llega
		 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		*/
		usuario.setPassword(generadorCript.encriptador().encode(usuario.getPassword()));
		
		
		usuarioDAO.save(usuario);
		
		return "redirect:/usuarios";
	}
	
	@PostMapping("/usuarios/editar")
	public String editarUsuario(@ModelAttribute Usuario usuario) {
		usuarioDAO.save(usuario);
		
		
		return "redirect:/usuarios";
		
		
	}
	

	/*Esta ruta incopora la seguridad de que el usuario que esta loggeado solo tenga acceso a modificar su propio usuario*/
	@GetMapping("/usuarios/editar/{id}")
	public ModelAndView usuariosEditar(@PathVariable String id, Autenticacion authentication) {
		
		
		
		
		String quien = authentication.getName();
		List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>)authentication.getAuthorities();
		System.out.println(grantedAuthorities);
		
		if(!quien.equalsIgnoreCase(id)) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/usuarios");
			
			return mav;
		}
		
		
		
		Usuario user = usuarioDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editarUser");
		mav.addObject("user",user);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);
		
		return mav;
	}	
	
	
	@GetMapping("/usuarios/borrar/{usuario}")
	public String borarMensaje(@PathVariable String usuario) {
		usuarioDAO.deleteById(usuario);
		return "redirect:/usuarios";
	}

}
