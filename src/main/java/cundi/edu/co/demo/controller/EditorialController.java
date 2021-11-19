package cundi.edu.co.demo.controller;

import cundi.edu.co.demo.dto.EditorialDto;
import cundi.edu.co.demo.entity.Editorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private IEditorialService service;

    @GetMapping(value = "/obtenerPaginado" ,produces = "application/json")
    public ResponseEntity<?> retonarPaginado(Pageable page) {
        Page<Editorial> listaEditorial = service.retornarPaginado(page);
        return new ResponseEntity<Page<Editorial>>(listaEditorial, HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPorId/{id}" ,produces = "application/json")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) throws ModelNotFoundException {
        EditorialDto editorialDto = service.retonarPorId(id);
        return new ResponseEntity<EditorialDto>(editorialDto, HttpStatus.OK);
    }

    @PostMapping(value = "/insertar", consumes = "application/json")
    public ResponseEntity<?> guardar(@Valid @RequestBody Editorial editorial) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
        service.guardar(editorial);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/editar", consumes = "application/json")
    public ResponseEntity<?> editar(@Valid @RequestBody Editorial editorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
        service.editar(editorial);
        return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id)  throws ModelNotFoundException, ConflictException{
        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
