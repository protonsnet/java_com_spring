/*
 * Interface Repositório do Usuario
 * Extende a Classe JpaRepository
 * Possui métodos já implementados de persistência no banco de dados
 */

package br.com.springboot.Protonsnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.Protonsnet.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
