package com.diego.muroMensajes.usuarios;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.jandex.TypeTarget.Usage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	
	@GetMapping("/consultas")
	public ModelAndView consultas() {
		/*Cuando escriba esta ruta me va a sacar esta lista por consola.
		 * Este resultado de las consultas lo vamos a guardar en arrayList o en Arays 
		 * y lo vamos a visualizar por consola*/
		
		List<Usuario> resultado = (List<Usuario>) usuarioDAO.findByEdad(10);
		List<Usuario> resultado2 = (List<Usuario>) usuarioDAO.findByEdadLessThan(40);
		List<Usuario> resultado3 = (List<Usuario>) usuarioDAO.findByUsuarioContaining("u");
		/*Queria que salieran los 3 mas grandes y no funcionó*/
		List<Usuario> resultado4 = (List<Usuario>) usuarioDAO.findTop3ByEdadLessThan(1000);
		System.out.println(resultado);
		System.out.println(resultado2);
		System.out.println(resultado3);
		System.out.println(resultado4);
		
		
		ModelAndView nav = new ModelAndView();
		
		nav.setViewName("usuariosPrueba");
		

		List<Usuario> listaUsuariosPrueba = (List<Usuario>)usuarioDAO.buscarPorEdadASD(20);
		nav.addObject("usuarios", listaUsuariosPrueba);
		System.out.println(listaUsuariosPrueba);
		
		
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
	public ModelAndView usuariosEditar(@PathVariable String id, Authentication authentication) {
		
		
		
		
		String quien = authentication.getName();
		
		List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>)authentication.getAuthorities();
	
		System.out.println(grantedAuthorities);
		
		/*if(!quien.equalsIgnoreCase(id)) {
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/usuarios");
			
			return mav;
		}
		*/
		
		
		Usuario user = usuarioDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editarUser");
		mav.addObject("user",user);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);
			
		return mav;
	}	
	
	
	@GetMapping("/usuarios/borrar/{usuario}")
	public String borarMensaje(@PathVariable String usuario, Authentication authentication) {
		
		String quien = authentication.getName();
		List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>)authentication.getAuthorities();
		System.out.println(grantedAuthorities);
		
		if(!quien.equalsIgnoreCase(usuario)) {
			
			System.out.println("No puedes hacer esto");
			return "redirect:/usuarios";
	}else{
		
		usuarioDAO.deleteById(usuario);
		
		return "redirect:/usuarios";

	}
	
	}

}
