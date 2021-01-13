package com.estoque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estoque.domain.Usuario;
import com.estoque.repository.UsuarioRepository;
import com.estoque.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario cli = repo.findByNome(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getNome(), cli.getSenha(), cli.getPerfis());
	}

}
