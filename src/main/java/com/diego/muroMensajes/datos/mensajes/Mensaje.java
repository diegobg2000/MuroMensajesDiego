package com.diego.muroMensajes.datos.mensajes;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mensaje")
public class Mensaje {
@Id
@Column
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;

@Column(name="texto")
private String texto;



/*GETTES Y SETTERS*/
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getTexto() {
	return texto;
}

public void setTexto(String texto) {
	this.texto = texto;
}

}
