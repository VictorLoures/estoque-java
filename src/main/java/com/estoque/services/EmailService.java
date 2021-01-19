package com.estoque.services;

import org.springframework.mail.SimpleMailMessage;

import com.estoque.domain.Usuario;

public interface EmailService {


	void recuperarSenha(Usuario user);

	void sendEmail(SimpleMailMessage message);
}
