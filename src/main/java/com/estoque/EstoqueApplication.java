package com.estoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estoque.domain.Categoria;
import com.estoque.domain.Produtos;
import com.estoque.domain.Usuario;
import com.estoque.repository.CategoriaRepository;
import com.estoque.repository.ProdutosRepository;
import com.estoque.repository.UsuarioRepository;

@SpringBootApplication
public class EstoqueApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutosRepository produtosRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}
	
	public void run(String ... args) {
		
		Usuario u1 = new Usuario(null, "Erica");
		
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
		
		u1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		
		Usuario u2 = new Usuario(null, "Victor");
		
		Categoria cat3 = new Categoria(null, "Movéis");
		Categoria cat4 = new Categoria(null, "Peças de Carro");
		
		Produtos p4 = new Produtos(null, "Roda", 200.00, 1);
		Produtos p5 = new Produtos(null, "Cama", 800.00, 1);
		Produtos p6 = new Produtos(null, "Mesa", 30.00, 1);
		
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p4));
		
		p4.getCategoria().addAll(Arrays.asList(cat4));
		p5.getCategoria().addAll(Arrays.asList(cat3));
		p6.getCategoria().addAll(Arrays.asList(cat3));
		
		u2.getProdutos().addAll(Arrays.asList(p4, p5, p6));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		produtosRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
	}

}
