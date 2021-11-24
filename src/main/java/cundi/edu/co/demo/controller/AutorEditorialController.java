package cundi.edu.co.demo.controller;

import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.service.IAutorEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/autores-editoriales")
public class AutorEditorialController {

    @Autowired
    private IAutorEditorialService service;

    @PostMapping(value = "/insertar", consumes = "application/json")
    public ResponseEntity<?> guardar(@Valid @RequestBody AutorEditorial autorEditorial) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
        service.guardar(autorEditorial);
        return new ResponseEntity<Object>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/eliminarNativo/{idAutor}/{idEditorial}")
    public ResponseEntity<?> eliminarNativo(@PathVariable int idAutor,
                                            @PathVariable int idEditorial)  throws ModelNotFoundException {
        service.eliminarNativo(idAutor, idEditorial);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/obtener" ,produces = "application/json")
    public ResponseEntity<?> obtener(Pageable page) {
        Page<AutorEditorial> listaAutorEditorial = service.retornarPaginado(page);
        return new ResponseEntity<Page<AutorEditorial>>(listaAutorEditorial, HttpStatus.OK);
    }

    @GetMapping(value = "/obtenerPorIdCompuesto/{idAutor}/{idEditorial}" ,produces = "application/json")
    public ResponseEntity<?> obtenerPorIdCompuesto(@PathVariable int idAutor,
                                                   @PathVariable int idEditorial) throws ModelNotFoundException{
        AutorEditorial autorEditorial = service.obtenerPorIdCompuesto(idAutor, idEditorial);
        return new ResponseEntity<AutorEditorial>(autorEditorial, HttpStatus.OK);
    }
}
