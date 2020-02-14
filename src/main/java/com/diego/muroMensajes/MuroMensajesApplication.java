package com.diego.muroMensajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MuroMensajesApplication {

	public static void main(String[] args) {
			SpringApplication.run(MuroMensajesApplication.class, args);

			Logger logger = LoggerFactory.getLogger("hola");
			
			logger.info("***************** STARTING *******************");
			logger.info("Todo va bien");
	}

}
