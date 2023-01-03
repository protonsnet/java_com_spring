/*
 * Interface Repositório do Usuario
 * Extende a Classe JpaRepository
 * Possui métodos já implementados de persistência no banco de dados
 */

package br.com.springboot.Protonsnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.Protonsnet.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	/*
	 * buscarPorNome
	 * Criando a interface de consulta por parte do nome.
	 * ?1 para um único parâmetro "nome"
	 */
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarPorNome(String name);

}
