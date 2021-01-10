package com.estoque.dto;

import java.io.Serializable;

public class CategoriaDTO implements Serializable{
 static final long serialVersionUID = 1L;
 
	private Integer id;
	private String nome;
	
	public CategoriaDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	
	

}
