package cundi.edu.co.demo.controller;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.view.AutorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.service.IAutorService;

import javax.validation.Valid;
import java.util.List;

//@PreAuthorize("hasAuthority('Administrador')")
@RestController
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	private IAutorService service;
	
	//@PreAuthorize("hasAuthority('Administrador')  OR hasAuthority(' ') ")
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<?> retonarPaginado(Pageable page) {
		Page<Autor> listaAutor = service.retornarPaginado(page);
		return new ResponseEntity<Page<Autor>>(listaAutor, HttpStatus.OK);	
	}

	//@PreAuthorize("hasAuthority('Administrador')  OR hasAuthority('Vendedor') ")
	@GetMapping(value = "/obtenerSinListaInterna" ,produces = "application/json")
	public ResponseEntity<?> obtenerSinListaInterna(Pageable page) {
		Page<AutorDto> listaAutor = service.obtenerSinListaInterna(page);
		return new ResponseEntity<Page<AutorDto>>(listaAutor, HttpStatus.OK);
	}

	//@PreAuthorize("hasAuthority('Administrador')  OR hasAuthority('Vendedor') ")
	@GetMapping(value = "/obtenerSinListaInterna2" ,produces = "application/json")
	public ResponseEntity<?> obtenerSinListaInterna2(Pageable page) {
		Page<AutorView> listaAutor = service.obtenerSinListaInterna2(page);
		return new ResponseEntity<Page<AutorView>>(listaAutor, HttpStatus.OK);
	}

	@GetMapping(value = "/traerTodo" ,produces = "application/json")
	public ResponseEntity<?> traerTodo(Pageable page) {
		List<Autor> listaAutor = service.traerTodo();
		return new ResponseEntity<List<Autor>>(listaAutor, HttpStatus.OK);
	}

	@GetMapping(value = "/obtenerPorId/{id}" ,produces = "application/json")
	public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) throws ModelNotFoundException {
		AutorDto autorDto = service.retonarPorId(id);
		return new ResponseEntity<AutorDto>(autorDto, HttpStatus.OK);
	}

	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<?> guardar(@Valid @RequestBody Autor autor) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
		service.guardar(autor);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<?> editar(@Valid @RequestBody Autor autor) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		service.editar(autor);
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/eliminar/{i}")
	public ResponseEntity<?> eliminar(@PathVariable int i)  throws ModelNotFoundException, ConflictException{
		service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
