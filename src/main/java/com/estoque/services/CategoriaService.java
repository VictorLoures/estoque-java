package com.estoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.domain.Categoria;
import com.estoque.dto.CategoriaDTO;
import com.estoque.repository.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriasRepository;
	@Autowired
	private NativeScriptService nss;
	
	
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
		return categoriasRepository.save(obj);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {		
		return new Categoria(objDto.getId(), objDto.getNome(), objDto.getUsuario());
	}
	
	public void delet(Integer id) {
		nss.execute("DELETe FROM produtos WHERE categoria_id = " + id + ";");
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
