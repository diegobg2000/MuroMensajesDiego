package com.diego.muroMensajes.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class GeneradorCript {
	@Bean
	public  BCryptPasswordEncoder encriptador() {
	BCryptPasswordEncoder encript = new BCryptPasswordEncoder();
	return encript;
	}
		

}
