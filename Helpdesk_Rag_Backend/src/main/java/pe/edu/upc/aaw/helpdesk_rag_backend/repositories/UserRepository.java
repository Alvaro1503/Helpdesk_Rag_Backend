package pe.edu.upc.aaw.helpdesk_rag_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.aaw.helpdesk_rag_backend.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	public Users findByMail(String mail);
	
	//BUSCAR POR NOMBRE
	@Query("select count(u.mail) from Users u where u.mail =:username")
	public int buscarUsername(@Param("username") String nombre);
	
	
	//INSERTAR ROLES
	@Transactional
	@Modifying
	@Query(value = "insert into roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
	void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);

}