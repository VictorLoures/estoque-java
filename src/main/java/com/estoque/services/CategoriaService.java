package com.estoque.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.domain.Categoria;
import com.estoque.domain.Usuario;
import com.estoque.dto.CategoriaDTO;
import com.estoque.repository.CategoriaRepository;
import com.estoque.repository.UsuarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriasRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		List<Usuario> users = usuarioRepository.findAll();
		for(Usuario user : users) {
			if(user.getNome().equals(obj.getUsuario())) {
				user.getCategorias().addAll(Arrays.asList(obj));
			}
		}
		return categoriasRepository.save(obj);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {		
		return new Categoria(objDto.getId(), objDto.getNome(), objDto.getUsuario());
	}
	
	public void delet(Integer id) {
		categoriasRepository.deleteById(id);
	}
	
	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoriasRepository.save(newObj);
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		newObj.setUsuario(obj.getUsuario());
	}

}
