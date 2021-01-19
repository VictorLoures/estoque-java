package com.estoque.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.estoque.domain.Usuario;


public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void recuperarSenha(Usuario user) {
		SimpleMailMessage sm = prepareSimpleMailMessage(user);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(Usuario user) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Recuperação de Senha");
		sm.setText("Nova senha: " + user.getSenha());
		return sm;	
	}
	
}
