package cundi.edu.co.demo.service.impl;

import cundi.edu.co.demo.dto.EditorialDto;
import cundi.edu.co.demo.entity.Editorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorEditorialRepo;
import cundi.edu.co.demo.repository.IEditorialRepo;
import cundi.edu.co.demo.service.IEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EditorialServiceImpl implements IEditorialService {

    @Autowired
    private IEditorialRepo repo;

    @Autowired
    private IAutorEditorialRepo autorEditorialRepo;

    @Override
    public Page<Editorial> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Page<Editorial> retornarPaginado(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public EditorialDto retonarPorId(Integer idEditorial) throws ModelNotFoundException {
        Boolean existe = repo.existsById(idEditorial);
        if (existe == false){
            throw new ModelNotFoundException("No existe la editorial");
        }
        Editorial editorial = repo.findById(idEditorial).get();
        EditorialDto editorialDto = convertToEditorialDto(editorial);
        return editorialDto;
    }

    @Override
    public void guardar(Editorial editorial) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {
        Boolean existeCorreo = repo.existsByCorreo(editorial.getCorreo());
        Boolean existeNit = repo.existsByNit(editorial.getNit());
        if (existeCorreo == true){
            throw new ConflictException("El correo " + editorial.getCorreo() + " ya ha sido registrado");
        }
        if (existeNit == true) {
            throw new ConflictException("El NIT " + editorial.getNit() + " ya ha sido registrado");
        }
        repo.save(editorial);
    }

    @Override
    public void editar(Editorial editorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
        if(editorial.getId() != null){
            if(validarExistenciaPorId(editorial.getId())) {

                Editorial editorialAux = this.repo.findById(editorial.getId()).get();
                editorial.setNit(editorialAux.getNit());

                if(editorial.getCorreo().equals(editorialAux.getCorreo()))
                    this.repo.save(editorial);
                else {
                    if(!repo.existsByCorreo(editorial.getCorreo())) {
                        this.repo.save(editorial);
                    } else {
                        throw new ConflictException("Correo ya existe");
                    }
                }

            } else
                throw new ModelNotFoundException("Editorial no encontrada");
        } else {
            throw new ArgumentRequiredException("Id de la editorial es requerida");
        }
    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException, ConflictException {
        if (validarExistenciaTablaIntermedia(id) == false) {
            if (validarExistenciaPorId(id)) {
                this.repo.deleteById(id);
            } else {
                throw new ModelNotFoundException("La editorial con el id " + id + " no fue encontrado");
            }
        } else {
            throw new ConflictException("No se puede eliminar por referencia en tabla intermedia");
        }
    }

    private Boolean validarExistenciaPorId(int idEditorial) {
        return repo.existsById(idEditorial);
    }

    private Boolean validarExistenciaTablaIntermedia(int idEditorial) {
        return autorEditorialRepo.existsByIdEditorial(idEditorial);
    }

    private EditorialDto convertToEditorialDto(final Editorial editorial) {
        final EditorialDto editorialDto = new EditorialDto();
        editorialDto.setNit(editorial.getNit());
        editorialDto.setNombre(editorial.getNombre());
        editorialDto.setCorreo(editorial.getCorreo());
        return editorialDto;
    }
}
