package com.diego.muroMensajes.datos.mensajes;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MensajeRutas {
	
	@Autowired
	private MensajeDAO mensajeDAO;
	
	
	@GetMapping("/mensajes")
	/*ModelAndView --> Estas devolviendo de una vez vista y datos
	En este retornas un solo objeto*/
	public ModelAndView todosLosMensajes() {
		
		ModelAndView nav = new ModelAndView();
		nav.setViewName("mensajes");
		//Relacionado con el Post de abajo
		nav.addObject("mensaje", new Mensaje());

		
		List<Mensaje> listaMensajes = (List<Mensaje>)mensajeDAO.findAll();
		nav.addObject("mensajes",listaMensajes);
		return nav;
	}
	

	
	@GetMapping("mensajes/borrar/{id}")
	public String  borrarMensaje(@PathVariable long id) {
		
		mensajeDAO.deleteById(id)
		;
		return  "redirect:/mensajes";
	}
	

	
	
	
	
	
	
	
	
	@PostMapping("/mensajes/anadir")
	public String crearMensaje(@ModelAttribute Mensaje mensaje) {
		
		mensajeDAO.save(mensaje);
		
		return "redirect:/mensajes";
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/mensajes2")
	//ModelAndView --> Estas devolviendo de una vez vista y datos
	public String todosLosMensajes(ModelMap map) {
		
		return "mensajes";
	}
	
	
	
	
	
}
	

