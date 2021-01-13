package com.estoque.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estoque.domain.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer>{

	@Transactional
	@Query(value = "select * from produtos order by nome", nativeQuery = true)
	public List<Produtos> oPNome();
	
	@Transactional
	@Query(value = "select * from produtos order by id", nativeQuery = true)
	public List<Produtos> oPCodigo();
	
	
	
}
