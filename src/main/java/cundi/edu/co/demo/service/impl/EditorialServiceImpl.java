package cundi.edu.co.demo.service.impl;

import cundi.edu.co.demo.dto.EditorialDto;
import cundi.edu.co.demo.entity.Editorial;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
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

    @Override
    public Page<Editorial> retornarPaginado(int page, int size) {
        return null;
    }

    @Override
    public Page<Editorial> retornarPaginado(Pageable page) {
        return null;
    }

    @Override
    public EditorialDto retonarPorId(Integer integer) throws ModelNotFoundException {
        return null;
    }

    @Override
    public void guardar(Editorial editorial) throws ConflictException, ModelNotFoundException, ArgumentRequiredException {

    }

    @Override
    public void editar(Editorial editorial) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {

    }

    @Override
    public void eliminar(int id) throws ModelNotFoundException {

    }
}
