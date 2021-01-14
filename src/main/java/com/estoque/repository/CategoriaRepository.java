package com.estoque.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estoque.domain.Categoria;
import com.estoque.domain.Produtos;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Transactional
	@Query(value = "create table userlogado", nativeQuery = true)
	public void usuarioLogado();
	@Transactional
	@Query(value = "insert into userlogado values = ${user}", nativeQuery = true)
	public List<Produtos> insertUser( String user);
	
	

}
