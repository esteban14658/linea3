package cundi.edu.co.demo.service.impl;

import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorEditorialRepo;
import cundi.edu.co.demo.service.IAutorEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AutorEditorialServiceImpl implements IAutorEditorialService {

    @Autowired
    private IAutorEditorialRepo repo;

    @Override
    public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException {
        //Validaciones pertinentes
        this.repo.eliminarNativa(idAutor, idEditorial);
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
        return null;
    }

    @Override
    public AutorEditorialDto retonarPorId(Integer integer) throws ModelNotFoundException {
        return null;
    }

    @Override
    public void guardar(AutorEditorial autorEditorial) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
        //No se puede realizar por que no tenemos la doble referencia
        //this.repo.save(aux);

        //Se podria hacer el find de autor y de editorial y asociar pero traeria  mucha informacion solo para usar dos ID's

        //Agregar validaciones respectivas
        this.repo.guardarNativo(autorEditorial.getAutor().getId(), autorEditorial.getEditorial().getId(),
                autorEditorial.getFecha());
    }

    @Override
    public void editar(AutorEditorial autorEditorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {

    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException {

    }
}
