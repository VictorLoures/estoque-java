package com.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque.domain.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer>{

}
