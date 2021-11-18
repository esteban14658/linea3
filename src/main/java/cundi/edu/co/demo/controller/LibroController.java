package cundi.edu.co.demo.controller;

import cundi.edu.co.demo.dto.LibroDto;
import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private ILibroService service;

    @GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
    public ResponseEntity<?> retonarPaginado(Pageable page) {
        Page<Libro> listaLibro = service.retornarPaginado(page);
        return new ResponseEntity<Page<Libro>>(listaLibro, HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPorId/{id}" ,produces = "application/json")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) throws ModelNotFoundException {
        LibroDto libroDto = service.retonarPorId(id);
        return new ResponseEntity<LibroDto>(libroDto, HttpStatus.OK);
    }

    @PostMapping(value = "/insertar/{idAutor}", consumes = "application/json")
    public ResponseEntity<?> guardar(@Valid @RequestBody Libro libro,
                                     @PathVariable Integer idAutor) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
        service.guardarPersonalizado(libro, idAutor);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/editar/{idAutor}", consumes = "application/json")
    public ResponseEntity<?> editar(@Valid @RequestBody Libro libro,
                                     @PathVariable Integer idAutor) throws ModelNotFoundException, ArgumentRequiredException {
        service.editarPersonalizado(libro, idAutor);
        return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
    }
}
