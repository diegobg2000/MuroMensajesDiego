package com.diego.muroMensajes.usuarios;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface UsuarioDAO extends CrudRepository<Usuario, String>{
	/*Necesita dos parametros:
	 * uno viejo para que lo encuentr y
	 * uno nuevo para que lo actualice */
	@Transactional/*Hay que indicarle que haciendo una transaccion lo que implica abrirlo
	ycerralo como haciamos en JPA*/
	@Query(value="Update usuario u set usuario= '?2' WHERE usuario='?1'", nativeQuery = true)
	void actualiazrNombreUsuarioSQL(String userViejo, String userNuevo);
	
	/*Si construimos de esta forma las sentencias SQL que necesitemos evitamos que nos puedan
	 * realizar inyecciones de SQL*/
	@Transactional
	@Query(value="select * from usuarios where edad=:edad", nativeQuery = true)
	/*Si queremos sacarlo como siso tenemos que dejarlo en void
	 * Pero si queremos pasarlo a thimeaf tenemos que retornar una lista de ese objeto*/
	List<Usuario> buscarPorEdad(@Param("edad") Integer anios);
	
	@Transactional
	@Query(value="select * from usuario where edad=:edad", nativeQuery = true)
	/*Si queremos sacarlo como siso tenemos que dejarlo en void
	 * Pero si queremos pasarlo a thimeaf tenemos que retornar una lista de ese objeto*/
	List<Usuario> buscarPorEdadASD(@Param("edad") Integer anios);
	
	
	
	
	
	/*Estoy definiendo una operacion que pretende devolverme ua lista de usuarios que pretende buscar 
	 * por una edad concreta
	 * 
	 * Menor e igual --> findByIdLessThanEqual
	 * Menor --> less
	 * Mayor --> Greater
	 * Buscar por edad o por usuario findByEdadOrUsuario
	 * Acaban con --> findiByUsuarioEndsWith(String cadena
	 * Buscar las tres mayores de la edad que le pasamos --> findTop3ByEdadGreaterThan(Integer edad)
	 * Para contar los que hay de cierta edad --> countByEdad(Integer edad)*/
	List<Usuario> findByEdad(Integer edad);
	List<Usuario> findByEdadLessThan(Integer edad);
	List<Usuario> findByEdadAndUsuario(Integer edad, String usuario);

	List<Usuario> findByUsuarioContaining(String cadena);
	
	List<Usuario> findTop3ByEdadGreaterThan(Integer edad);
	List<Usuario> findTop3ByEdadLessThan(Integer edad);

	
	

	

}
