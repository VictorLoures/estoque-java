package com.estoque.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estoque.domain.Usuario;
import com.estoque.dto.UsuarioDTO;
import com.estoque.repository.UsuarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private EmailService emailservice;
	
	public Usuario find(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public List<Usuario> findAll() throws ObjectNotFoundException {
		if(usuarioRepository.findAll() == null) {
			return null;
		}else {
			List<Usuario> obj = usuarioRepository.findAll();
			return obj;
		}
	}
	
	public void delet(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	public void insert(Usuario obj) {
		obj.setSenha(pe.encode(obj.getSenha()));
		usuarioRepository.save(obj);
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {		
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getSenha(), objDto.getEmail());
	}
	
	public Usuario update(Usuario obj) throws ObjectNotFoundException {		
		Usuario newObj = find(obj.getId());	
		updateData(newObj, obj);
		return usuarioRepository.save(newObj);
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setSenha(pe.encode(obj.getSenha()));
		newObj.setEmail(obj.getEmail());
	}
	
	public Integer retornaId(String nome) {
		Integer id = 0;
		List<Usuario> users = usuarioRepository.findAll();
		for(Usuario user : users) {
			if(user.getNome().equals(nome)) {
				id = user.getId();
			}
		}
		return id;
	}
	
	public void recuperaSenha(String email) throws ObjectNotFoundException {
		List<Usuario> users = usuarioRepository.findAll();
		for(Usuario user : users) {
			if(user.getEmail().equals(email)) {
				user.setSenha(senhaNova());				
				emailservice.recuperarSenha(user);
				update(user);
			}
		}		
	}
	
	private String senhaNova() {
		Random gerador = new Random();
		String senha ="";
		String s1 ="" + gerador.nextInt(5);
		String s2 ="" + gerador.nextInt(5);
		String s3 ="" + gerador.nextInt(5);
		String s4 ="" + gerador.nextInt(5);
		String s5 ="" + gerador.nextInt(5);
		
		senha = s1 + s2 + s3 + s4 + s5;
        
        return senha;
	}
}
