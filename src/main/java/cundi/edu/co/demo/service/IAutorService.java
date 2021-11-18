package cundi.edu.co.demo.service;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.view.AutorView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAutorService extends ICrud<Autor, AutorDto, Integer> {

    public Page<AutorDto> obtenerSinListaInterna(Pageable page);

    public Page<AutorView> obtenerSinListaInterna2(Pageable page);

    public List<Autor> traerTodo();

}
