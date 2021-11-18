package cundi.edu.co.demo.service;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.dto.LibroDto;
import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface ILibroService extends ICrud<Libro, LibroDto, Integer>{

    public void guardarPersonalizado(Libro libro, Integer idAutor) throws ModelNotFoundException, ArgumentRequiredException;

    public void editarPersonalizado(Libro libro, Integer idAutor) throws ModelNotFoundException, ArgumentRequiredException;

    public void eliminarPersonalizado(int idAutor, int idLibro) throws ModelNotFoundException;
}
