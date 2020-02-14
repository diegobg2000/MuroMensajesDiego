package com.diego.muroMensajes.datos.mensajes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeDAO extends CrudRepository<Mensaje, Long>{
	

}

