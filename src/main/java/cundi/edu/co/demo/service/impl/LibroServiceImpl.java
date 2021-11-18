package cundi.edu.co.demo.service.impl;

import cundi.edu.co.demo.dto.LibroDto;
import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.repository.ILibroRepo;
import cundi.edu.co.demo.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements ILibroService {

    @Autowired
    private ILibroRepo repo;

    @Autowired
    private IAutorRepo repoAutor;

    @Override
    public Page<Libro> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Page<Libro> retornarPaginado(Pageable page) {
        Page<Libro> resultado = repo.findAll(page);
        return resultado;
    }

    @Override
    public LibroDto retonarPorId(Integer idLibro) throws ModelNotFoundException {
        Boolean existe = repo.existsById(idLibro);
        if (existe == false){
            throw new ModelNotFoundException("No existe el libro");
        }
        Libro libro = repo.findById(idLibro).get();
        LibroDto libroDto = convertToLibroDto(libro);
        return libroDto;
    }

    @Override
    public void guardar(Libro libro) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {

    }


    @Override
    public void editar(Libro libro) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
        if(libro.getId() != null) {
            if(validarExistenciaPorId(libro.getId())) {
                Boolean existeAutor = repoAutor.existsById(libro.getAutor().getId());
                if (existeAutor == false) {
                    throw new ModelNotFoundException("El id " + libro.getAutor().getId() + " del autor no existe");
                }
                if (libro.getId() == null) {
                    throw new ArgumentRequiredException("El id " + libro.getId() + " del libro es requerido");
                }
                this.repo.save(libro);
            }
        }
    }

    @Override
    public void eliminar(int idAutor) throws ModelNotFoundException {

    }

    public void guardarPersonalizado(Libro libro, Integer idAutor) throws ModelNotFoundException, ArgumentRequiredException{
        Boolean existeAutor = repoAutor.existsById(idAutor);
        if (existeAutor == false){
            throw new ModelNotFoundException("El id " + idAutor + " del autor no existe");
        }
        Autor autor = repoAutor.findById(idAutor).get();
        libro.setAutor(autor);
        repo.save(libro);
    }

    public void editarPersonalizado(Libro libro, Integer idAutor) throws ModelNotFoundException, ArgumentRequiredException {
        if(libro.getId() != null) {
            if(validarExistenciaPorId(libro.getId())) {
                Boolean existeAutor = repoAutor.existsById(idAutor);
                if (existeAutor == false) {
                    throw new ModelNotFoundException("El id " + idAutor + " del autor no existe");
                }
                if (libro.getId() == null) {
                    throw new ArgumentRequiredException("El id " + libro.getId() + " del libro es requerido");
                }
                Autor autor = repoAutor.findById(idAutor).get();
                libro.setAutor(autor);
                this.repo.save(libro);
            }
        }
    }

    public void eliminarPersonalizado(int idAutor, int idLibro) throws ModelNotFoundException{
        if(validarExistenciaPorIdAutor(idAutor)){
            if(validarExistenciaPorId(idLibro)){
                this.repo.deleteById(idLibro);
            }
            else {
                throw new ModelNotFoundException("El libro con el id " + idLibro + " no fue encontrado");
            }
        }
        else {
            throw new ModelNotFoundException("El autor con el id " + idAutor + " no fue encontrado");
        }
    }

    private Boolean validarExistenciaPorId(int idLibro) {
        return repo.existsById(idLibro);
    }

    private Boolean validarExistenciaPorIdAutor(int idAutor) {
        return repoAutor.existsById(idAutor);
    }

    private LibroDto convertToLibroDto(final Libro libro){
        final LibroDto libroDto = new LibroDto();
        libroDto.setNombre(libro.getNombre());
        libroDto.setDescripcion(libro.getDescripcion());
        libroDto.setNumeroPaginas(libro.getNumeroPaginas());
        libroDto.setAutor(libro.getAutor().getId());
        return libroDto;
    }
}
