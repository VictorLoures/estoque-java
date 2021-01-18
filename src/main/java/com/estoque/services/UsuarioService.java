package com.estoque.services;

import java.util.List;
import java.util.Optional;

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


}
