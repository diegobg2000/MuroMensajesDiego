package com.diego.muroMensajes.datos.rol;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.diego.muroMensajes.usuarios.Usuario;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@Column
	private String nombre = "USER";
	
	/*RELACIONES ENTRE TABLAS*/
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "rol")
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	
	
	/*
	public void addUsuario(Usuario usuario) {

		if(!usuarios.contains(usuario)) {
			
			usuarios.add(usuario);
		}
	}	
	
	*/

/*GETTERS AND SETTERS*/


	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public List<Usuario> getUsuarios() {
		return usuarios;
	}





	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	







}







/*
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name="permisos", 
joinColumns = @JoinColumn(name= "FK_roles"),
inverseJoinColumns = @JoinColumn(name = "FK_usuarios"))
private List<Usuario> usuarios = new ArrayList<Usuario>();

}
*/
