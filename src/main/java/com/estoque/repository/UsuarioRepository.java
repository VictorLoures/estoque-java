package com.estoque.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Transactional
	Usuario findByNome(String nome);
	
}
