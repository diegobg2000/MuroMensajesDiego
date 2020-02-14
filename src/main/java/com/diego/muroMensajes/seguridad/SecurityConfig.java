package com.diego.muroMensajes.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.diego.muroMensajes.beans.GeneradorCript;
import com.diego.muroMensajes.servicios.Autenticacion;

@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter   {
	
		/*Creee un servicio que autentica, como es un servicio es inyectable 
		 * 
		 * Y en esta clase en si estoy explicacando como voy a encriptar las contraseñas*/
	
	
	/*Configuracion del acceso a nuestra aplicacion*/
	   @Autowired
	    private Autenticacion autenticacion;
	   
	   @Autowired
	   private GeneradorCript generadorCript;
	    
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    
	        
	    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        //Le digo quien es mi encoder
	    	provider.setPasswordEncoder(generadorCript.encriptador());
	    	//Le digo cual es mi servicio. Quien se encarga de ir a buscar a los usuario a la base de datos
	        provider.setUserDetailsService(autenticacion);
	    	
	    	auth.authenticationProvider(provider);
	    }   
	    
//		   @Override
//   	    protected void configure(HttpSecurity http) throws Exception {
//   	    	http
//   	        .authorizeRequests()/*COnfiguro las rutas de configuracion*/
//   	        	.antMatchers("/usuarios").permitAll()/*Si esa utorizacion de peticion de una ruta encaja con /usuarios. Entonces permite que entre todo el mundo*/
//   	        	.antMatchers("/mensajes").authenticated()/*Todo el mundo que este autenticado y yo tenga en mi sistema como usuario*/
//   	        	.antMatchers("/usuarios/**").hasAuthority("ADMIN")/*Para todas las operacion de usuario: borrar, añadir. Tienes que ser un ADMIN*/
//   	        	.antMatchers("/usuarios/anadir").hasAnyAuthority("ADMIN","MODERADOR")/*Permite realiazr la operacion tanto al ADMIN como al MODERADOR*/
//   		        .and() /*Le digo que queiero configurar otra cosa*/   	
//   	        .formLogin()/*Formulario de loggin configurration*/
//   	            .loginPage("/login")/*Indicamos cual es la druta de nuestra ventana de loggin*/
//   	            .permitAll()/*Permitimos acceso a esta ruta a todos los usuarios*/
//   	            .defaultSuccessUrl("/")/*Si esta pagina de loggin me permite entrar la pagina que visualizo cuando autentifica mis credenciales es "/" en este caso*/
//   	            .failureUrl("/login?error=true")/*Si falla la entrada controlamos la salida para que nos muestre en mensaje de error que nosotros deseamos*/
//   	            .usernameParameter("username")/*En el formulario que te voy a mandar va a ser un input que se va a llamr username*/
//   	            
//   	            .passwordParameter("password")/*Le indicamos que nombre tiene la contraseña*/
//   	            .and()
//   	        .logout()/*Configuracion del logout*/
//   	            .permitAll()
//   	            .logoutUrl("/logout")
//   	            .logoutSuccessUrl("/")/*Ruta a la que nos lleva cuando el logOut tiene exito*/
//   	            .and()
//   	        .csrf().disable();/*Desabilita el sistema de seguridad csrf que viene activado por defecto en Spring*/
//
//   	    }
//   	 


	

}


