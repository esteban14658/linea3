package cundi.edu.co.demo.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.entity.Usuario;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IUsuarioService;

@RestController
@RequestMapping("/registro")
public class RegistroController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<Object> guardar(@Valid @RequestBody Usuario usuario) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
		usuarioService.guardar(usuario);
		
		return new ResponseEntity<Object>(HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/obtener/{pagina}/{cantidad}", produces = "application/json")
	public ResponseEntity<Page<Usuario>> retornar(@PathVariable @Min(value = 0) int pagina,
			@PathVariable @Min(value = 0) int cantidad){
		Page<Usuario> usuario = usuarioService.retornarPaginado(pagina, cantidad);
		
		return new ResponseEntity<Page<Usuario>>(usuario, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable @Min(value = 0) int id) throws ModelNotFoundException, ConflictException{
		usuarioService.eliminar(id);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
}
