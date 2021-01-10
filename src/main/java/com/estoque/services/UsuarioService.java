package com.estoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.domain.Usuario;
import com.estoque.repository.UsuarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository categoriasRepository;
	
	public Usuario find(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> obj = categoriasRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public List<Usuario> findAll() throws ObjectNotFoundException {
		if(categoriasRepository.findAll() == null) {
			return null;
		}else {
			List<Usuario> obj = categoriasRepository.findAll();
			return obj;
		}
	}

}