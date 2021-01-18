package com.estoque.dto;

public class ProdutosDTO {
	
	private Integer id;
	private String nome;
	private Double preco;
	private Integer total;
	

	public ProdutosDTO(Integer id, String nome, Double preco, Integer total) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.total = total;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
