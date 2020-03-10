package com.diego.muroMensajes.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.diego.muroMensajes.datos.rol.Rol;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
	@Id
	@Column(name = "usuario")
	@Size(min=2, message="TIENE QUE TENER UN MINIMO DE DOS CARACTERES")
	@Size(max= 6, message="TIENE QUE TENER MENOS DE 7 CARACTERES")
	private String usuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String telefono;

	@Column(name="edad")
	private Integer edad;
	
	
	/*RELACIONES ENTRE TABLAS*/
	@ManyToOne
	private Rol rol = new Rol();

	
	/*GETTERS AND SETTERS*/



	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	



	
	/*Metodos implementados de UserDetails*/
	/*Metodos a los que tenemos acceso tras implementar UserDetails.*/


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*Se crea un ArrayList de GrantedAutohority*/
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		/*Se añade a la lista el los elementos de la clase rol*/
	    grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre()));
	    	    
	    return grantedAuthorities;
	}
	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	/*
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="permisos", 
	joinColumns = @JoinColumn(name= "FK_roles")),
	inverseJoinColumns = @JoinColumn(name = "FK_usuarios")
	private List<Rol> roles = new ArrayList<Rol>();
	*/


	/*To String*/
	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", edad=" + edad + "]";
	}
	
	
	
	
	/*Me va a imprimir el estado del objeto antes de updatearlo*/
	@PreUpdate
	public void antesDeUpdare() {
		System.out.println(this);
	}
	
	/*Me va a imprimir el estado del objeto despues de updatearlo*/
	@PostUpdate
	public void despuesDeUpdate() {
		System.out.println(this);
	}
	

	
}


