package cundi.edu.co.demo.service.impl;

import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorEditorialRepo;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.repository.IEditorialRepo;
import cundi.edu.co.demo.service.IAutorEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AutorEditorialServiceImpl implements IAutorEditorialService {

    @Autowired
    private IAutorEditorialRepo repo;

    @Autowired
    private IAutorRepo autorRepo;

    @Autowired
    private IEditorialRepo editorialRepo;

    @Override
    public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
        //Validaciones pertinentes
        if (validarExistenciaPorIdAutor(idAutor) == false){
            throw new ModelNotFoundException("El autor con el id " + idAutor +
                    " no existe");
        }
        if (validarExistenciaPorIdEditorial(idEditorial) == false){
            throw new ModelNotFoundException("La editorial con el id " + idEditorial +
                    " no existe");
        }
        if (validarExistenciaIdCompuesto(idAutor, idEditorial)) {
            this.repo.eliminarNativa(idAutor, idEditorial);
        } else {
            throw new ModelNotFoundException("la asignacion que desea eliminar, no existe");
        }
    }

    @Override
    public AutorEditorial obtenerPorIdCompuesto(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
        if (validarExistenciaPorIdAutor(idAutor) == false){
            throw new ModelNotFoundException("El autor con el id " + idAutor +
                    " no existe");
        }
        if (validarExistenciaPorIdEditorial(idEditorial) == false){
            throw new ModelNotFoundException("La editorial con el id " + idEditorial +
                    " no existe");
        }
        if (validarExistenciaIdCompuesto(idAutor, idEditorial)) {
            AutorEditorial autorEditorial = this.repo.obtenerPorIdCompuesto(idAutor, idEditorial);
            autorEditorial.getAutor().setLibro(null);
            return autorEditorial;
        } else {
            throw new ModelNotFoundException("la asignacion que desea obtener, no existe");
        }
    }

    @Transactional
    @Override
    public void asociarAutorEditoial() {
        this.repo.guardarNativo(1, 1, LocalDate.now());
        this.repo.guardarNativo(2, 1, LocalDate.now());
        this.repo.guardarNativo(3, 1, LocalDate.now());
        this.repo.guardarNativo(6, 1, LocalDate.now());
    }

    @Override
    public Page<AutorEditorial> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Page<AutorEditorial> retornarPaginado(Pageable page) {
        Page<AutorEditorial> resultado = repo.findAll(page);
        for (AutorEditorial a: resultado.getContent()) {
            a.getAutor().setLibro(null);
        }
        return resultado;
    }

    @Override
    public AutorEditorialDto retonarPorId(Integer integer) throws ModelNotFoundException {
        return null;
    }

    @Override
    public void guardar(AutorEditorial autorEditorial) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {

        if (validarExistenciaPorIdAutor(autorEditorial.getAutor().getId()) == false){
            throw new ModelNotFoundException("El autor con el id " + autorEditorial.getAutor().getId() +
            " no existe");
        }
        if (validarExistenciaPorIdEditorial(autorEditorial.getEditorial().getId()) == false){
            throw new ModelNotFoundException("La editorial con el id " + autorEditorial.getEditorial().getId() +
                    " no existe");
        }
        if (validarExistenciaIdCompuesto(autorEditorial.getAutor().getId(),
                autorEditorial.getEditorial().getId()) == false){
            this.repo.guardarNativo(autorEditorial.getAutor().getId(), autorEditorial.getEditorial().getId(),
                    LocalDate.now());
        } else {
            throw new ConflictException("la asignacion ya esta creada");
        }
    }

    @Override
    public void editar(AutorEditorial autorEditorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {

    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ConflictException {

    }

    private Boolean validarExistenciaPorIdAutor(Integer idAutor) {
        return this.autorRepo.existsById(idAutor);
    }

    private Boolean validarExistenciaPorIdEditorial(Integer idEditorial) {
        return this.editorialRepo.existsById(idEditorial);
    }

    private Boolean validarExistenciaIdCompuesto(Integer idAutor, Integer idEditorial){
        return this.repo.existsByIdCompuesto(idAutor, idEditorial);
    }
}
