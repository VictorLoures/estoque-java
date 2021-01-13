package com.estoque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.domain.Produtos;
import com.estoque.repository.ProdutosRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutosRepository categoriasRepository;
	
	public Produtos find(Integer id) throws ObjectNotFoundException {
		Optional<Produtos> obj = categoriasRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produtos.class.getName()));
	}
	
	public List<Produtos> findAll() throws ObjectNotFoundException {
		if(categoriasRepository.findAll() == null) {
			return null;
		}else {
			List<Produtos> obj = categoriasRepository.findAll();
			return obj;
		}
	}
	
	public void delet(Integer id) {
		categoriasRepository.deleteById(id);
	}
	
	public List<Produtos> ordenarPornome(){
		return categoriasRepository.oPNome();
	}
	
	public List<Produtos> ordenarPorCodigo(){
		return categoriasRepository.oPCodigo();
	}
}
