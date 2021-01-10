package com.estoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estoque.domain.Categoria;
import com.estoque.domain.Produtos;
import com.estoque.repository.CategoriaRepository;
import com.estoque.repository.ProdutosRepository;

@SpringBootApplication
public class EstoqueApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutosRepository produtosRepository;

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}
	
	public void run(String ... args) {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Cama, Mesa e Banho");
		
		Produtos p1 = new Produtos(null, "Computador", 2000.00, 1);
		Produtos p2 = new Produtos(null, "Impressora", 800.00, 1);
		Produtos p3 = new Produtos(null, "Toalha", 30.00, 1);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategoria().addAll(Arrays.asList(cat1));
		p2.getCategoria().addAll(Arrays.asList(cat1));
		p3.getCategoria().addAll(Arrays.asList(cat2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtosRepository.saveAll(Arrays.asList(p1, p2, p3));
		
	}

}
