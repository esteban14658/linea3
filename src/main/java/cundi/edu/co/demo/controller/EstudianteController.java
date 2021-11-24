package cundi.edu.co.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IEstudianteService;

@PreAuthorize("hasAuthority('Vendedor')")
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService service;
	
	//@RequestMapping(value = "/obtener", method = RequestMethod.GET)
	@GetMapping(value = "/obtener" , produces = "application/json")
	public ResponseEntity<List<Estudiante>> retonar() {
		List<Estudiante> listaEstudiante = service.retornarTodo();
		return new ResponseEntity<List<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/obtenerPaginado/{page}/{size}" ,produces = "application/json")
	public ResponseEntity<Page<Estudiante>> retonarPaginado(@PathVariable int page, @PathVariable int size) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page, size);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
	public ResponseEntity<Page<Estudiante>> retonarPaginado(Pageable page) {
		Page<Estudiante> listaEstudiante = service.retornarPaginado(page);
		return new ResponseEntity<Page<Estudiante>>(listaEstudiante, HttpStatus.OK);	
	}	
	
	@GetMapping(value = "/obtenerPorId/{idEstudiante}" ,produces = "application/json")
	public ResponseEntity<EstudianteDto> retonarPorId(@PathVariable int idEstudiante) throws ModelNotFoundException {
		EstudianteDto estudianteDto = service.retonarPorId(idEstudiante);
		return new ResponseEntity<EstudianteDto>(estudianteDto, HttpStatus.OK);
	}		
		
	
	
	
	//@RequestMapping(value = "/obtener", method = RequestMethod.GET)
	@GetMapping(value = "/obtener/{i}" ,produces = "application/json")
	public ResponseEntity<EstudianteDto> retonar(@PathVariable int i) throws ModelNotFoundException, Exception {
		EstudianteDto estudiante;
	    estudiante = service.retornar(i);
		return new ResponseEntity<EstudianteDto>(estudiante, HttpStatus.OK);	
		//return ResponseEntity.ok(est);
	}
	
	@PostMapping(value = "/insertar", consumes = "application/json")
	public ResponseEntity<Object> guardar(@Valid @RequestBody Estudiante estudiante) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
		service.guardar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/editar", consumes = "application/json")
	public ResponseEntity<Object> editar(@Valid @RequestBody Estudiante estudiante)  
				throws ArgumentRequiredException, ModelNotFoundException, ConflictException{
		this.service.editar(estudiante);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}	
	
	//204
	@DeleteMapping(value = "/eliminar/{i}")

	public ResponseEntity<Object> eliminar(@PathVariable int i)  throws ModelNotFoundException, ConflictException{
		this.service.eliminar(i);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}	
	
	
	
	@DeleteMapping(value = "/eliminarHeader/{i}")
	public ResponseEntity<?> eliminarConHeader(@PathVariable int i) {
		//EstudianteDto est  = new EstudianteDto("Johans", "Gonzalez " + i);
		HttpHeaders header = new HttpHeaders();
		header.add("info1", "valor 1");
		header.add("info2", "valor 2");
		return new ResponseEntity<Object>(header, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/obtenerPorNombreYApellido/{nombre}/{apellido}")
	public ResponseEntity<?> obtenerPorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido)
						throws ModelNotFoundException{
		EstudianteDto estudianteDto;
		estudianteDto = this.service.retornarPorNombreYApellido(nombre,apellido);
		return new ResponseEntity<EstudianteDto>(estudianteDto, HttpStatus.OK);
	}

	@GetMapping(value = "/estudiantesNativo")
	public ResponseEntity<?> estudiantesNativo(){
		List<EstudianteDto> listaEstudiantesDto;
		listaEstudiantesDto = this.service.estudiantesNativo();
		return new ResponseEntity<List<EstudianteDto>>(listaEstudiantesDto, HttpStatus.OK);
	}

	@GetMapping(value = "/estudiantesJPQL")
	public ResponseEntity<?> estudiantesJPQL(){
		List<EstudianteDto> listaEstudiantesDto;
		listaEstudiantesDto = this.service.estudiantesJPQL();
		return new ResponseEntity<List<EstudianteDto>>(listaEstudiantesDto, HttpStatus.OK);
	}
}
