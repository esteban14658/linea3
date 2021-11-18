package cundi.edu.co.demo.service;

import cundi.edu.co.demo.dto.AutorEditorialDto;
import cundi.edu.co.demo.entity.AutorEditorial;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IAutorEditorialService extends ICrud<AutorEditorial, AutorEditorialDto, Integer>{

    public void eliminarNativo(Integer idAutor, Integer idEditorial) throws ModelNotFoundException;

    public void asociarAutorEditoial();
}
