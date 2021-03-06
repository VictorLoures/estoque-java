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

import com.estoque.domain.Categoria;
import com.estoque.dto.CategoriaDTO;
import com.estoque.security.JWTUtil;
import com.estoque.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private JWTUtil jwtUtil;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException{
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Categoria>> findAll() throws ObjectNotFoundException{
		List<Categoria> obj = categoriaService.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void insert(@Validated @RequestBody CategoriaDTO objDto) {
		objDto.setUsuario(jwtUtil.getUsername());
		Categoria obj = categoriaService.fromDTO(objDto);
		
		obj = categoriaService.insert(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@Validated @RequestBody CategoriaDTO objDto) throws ObjectNotFoundException {
		objDto.setUsuario(jwtUtil.getUsername());
		Categoria obj = categoriaService.fromDTO(objDto);		
		obj = categoriaService.update(obj);
		
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) throws ObjectNotFoundException {
		categoriaService.delet(id);		
	}
	
	}
	
	
	

