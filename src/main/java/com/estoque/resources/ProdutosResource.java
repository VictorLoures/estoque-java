package com.estoque.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.domain.Produtos;
import com.estoque.dto.ProdutosDTO;
import com.estoque.security.JWTUtil;
import com.estoque.services.NativeScriptService;
import com.estoque.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/produtos")
public class ProdutosResource {

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private NativeScriptService nss;
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produtos> find(@PathVariable Integer id) throws ObjectNotFoundException{
		Produtos obj = produtoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public void insert(@Validated @RequestBody ProdutosDTO objDto, @PathVariable Integer id){
		Produtos obj = produtoService.fromDTO(objDto);
		String nome = jwtUtil.getUsername();
		produtoService.insert(obj, id, nome);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@Validated @RequestBody ProdutosDTO objDto) throws ObjectNotFoundException{
		Produtos obj = produtoService.fromDTO(objDto);
		produtoService.update(obj);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Produtos>> findAll() throws ObjectNotFoundException{
		List<Produtos> obj = produtoService.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		nss.execute("DELETE FROM `produtos_cliente` WHERE produtos_id = " + id + " ;");
		produtoService.delet(id);		
	}
	
	@RequestMapping(value="/nome", method=RequestMethod.GET)
	public ResponseEntity<List<Produtos>> ordenarPorNome() throws ObjectNotFoundException{
		List<Produtos> obj = produtoService.ordenarPornome();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/codigo", method=RequestMethod.GET)
	public ResponseEntity<List<Produtos>> ordenarPorCodigo() throws ObjectNotFoundException{
		List<Produtos> obj = produtoService.ordenarPorCodigo();
		return ResponseEntity.ok().body(obj);
	}	
		
 
}
	

