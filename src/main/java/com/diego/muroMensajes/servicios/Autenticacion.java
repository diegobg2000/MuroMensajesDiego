package com.diego.muroMensajes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.diego.muroMensajes.usuarios.Usuario;
import com.diego.muroMensajes.usuarios.UsuarioDAO;

@Service
public class Autenticacion implements UserDetailsService{

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		/*Esto devulve un optinal que es un tipo de dato nuevo en JAVA 8
		 * Los optional son un tipo de datos que pueden o no estar presentes
		usuarioDAO.findById(username)
		Como es optional puedo preguntar si esta vacio o si esta presente si esta
		vacio es como si lo estubieramos comparando a null
		 */
		
		//Esto se hace para minimizar los accesos a la base de datos
		Optional<Usuario> user = usuarioDAO.findById(username);
		if(user.isPresent()){
			return user.get();
			
		}
		else throw new UsernameNotFoundException("" + username);
	}
	
	
}
