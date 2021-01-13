package com.estoque.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produtos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	private Integer total;
	
	

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categorias;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "produtos")
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Produtos() {
		
	}

	public Produtos(Integer id, String nome, Double preco, Integer qte) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.total = qte;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQte() {
		return total;
	}

	public void setQte(Integer qte) {
		this.total = qte;
	}

	public Categoria getCategoria() {
		return categorias;
	}

	public void setCategoria(Categoria categoria) {
		this.categorias = categoria;
	}
	
	

}
