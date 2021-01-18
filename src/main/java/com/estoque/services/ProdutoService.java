package com.estoque.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.domain.Categoria;
import com.estoque.domain.Produtos;
import com.estoque.domain.Usuario;
import com.estoque.dto.ProdutosDTO;
import com.estoque.repository.CategoriaRepository;
import com.estoque.repository.ProdutosRepository;
import com.estoque.repository.UsuarioRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Produtos find(Integer id) throws ObjectNotFoundException {
		Optional<Produtos> obj = produtosRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produtos.class.getName()));
	}
	
	public List<Produtos> findAll() throws ObjectNotFoundException {
		if(produtosRepository.findAll() == null) {
			return null;
		}else {
			List<Produtos> obj = produtosRepository.findAll();
			return obj;
		}
	}
	
	public void delet(Integer id) {
		produtosRepository.deleteById(id);
	}
	
	public List<Produtos> ordenarPornome(){
		return produtosRepository.oPNome();
	}
	
	public List<Produtos> ordenarPorCodigo(){
		return produtosRepository.oPCodigo();
	}
	
	public Produtos fromDTO(ProdutosDTO objDto) {		
		return new Produtos(objDto.getId(), objDto.getNome(), objDto.getPreco(), objDto.getTotal());
	}

	public void insert(Produtos obj, Integer id, String nome) {		
		List<Categoria> cats = categoriaRepository.findAll();
		for(Categoria cat : cats) {
			if(cat.getId().equals(id)) {
				obj.setCategoria(cat);	
			}
		}
		
		List<Usuario> users = usuarioRepository.findAll();
		for(Usuario user : users) {
			if(user.getNome().equals(nome)) {
				user.getProdutos().addAll(Arrays.asList(obj));	
			}
		}		
		
		produtosRepository.save(obj);		
	}
	
	public Produtos update(Produtos obj) throws ObjectNotFoundException {
		Produtos newObj = find(obj.getId());
		updateData(newObj, obj);
		return produtosRepository.save(newObj);
	}
	
	private void updateData(Produtos newObj, Produtos obj) {
		newObj.setNome(obj.getNome());
		newObj.setPreco(obj.getPreco());
		newObj.setTotal(obj.getTotal());
	}
}
