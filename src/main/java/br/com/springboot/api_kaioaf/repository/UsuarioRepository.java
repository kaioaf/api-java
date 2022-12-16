package br.com.springboot.api_kaioaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.api_kaioaf.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select user from Usuario user where upper(trim(user.nome)) like %?1%")
	List<Usuario> buscarPorNome(String name);

}
