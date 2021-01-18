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

import com.estoque.domain.Usuario;
import com.estoque.dto.UsuarioDTO;
import com.estoque.security.JWTUtil;
import com.estoque.services.UsuarioService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) throws ObjectNotFoundException{
		Usuario obj = usuarioService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() throws ObjectNotFoundException{
		List<Usuario> obj = usuarioService.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		usuarioService.delet(id);		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void insert(@Validated @RequestBody UsuarioDTO objDto) {
		Usuario obj = usuarioService.fromDTO(objDto);
		usuarioService.insert(obj);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@Validated @RequestBody UsuarioDTO objDto) throws ObjectNotFoundException {
		Usuario obj = usuarioService.fromDTO(objDto);
		Integer id = usuarioService.retornaId(jwtUtil.getUsername());
		obj.setId(id);
		usuarioService.update(obj);
	}

}
