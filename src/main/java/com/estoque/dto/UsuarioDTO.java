package com.estoque.dto;

public class UsuarioDTO {

	private Integer id;
	private String nome;
	private String senha;
	private String email;
	
	

	public UsuarioDTO(Integer id, String nome, String senha, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.setSenha(senha);
		this.setEmail(email);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
