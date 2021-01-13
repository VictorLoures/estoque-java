package com.estoque.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	private String senha;
	
	public CredenciaisDTO() {
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
