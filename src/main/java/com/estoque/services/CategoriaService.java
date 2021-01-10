package com.estoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.domain.Categoria;
import com.estoque.repository.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriasRepository;
	
	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = categoriasRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll() throws ObjectNotFoundException {
		if(categoriasRepository.findAll() == null) {
			return null;
		}else {
			List<Categoria> obj = categoriasRepository.findAll();
			return obj;
		}
	}

}