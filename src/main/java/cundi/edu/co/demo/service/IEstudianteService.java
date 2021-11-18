package cundi.edu.co.demo.service;

import java.util.List;

import cundi.edu.co.demo.dto.EstudianteDto;
import cundi.edu.co.demo.entity.Estudiante;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface IEstudianteService extends ICrud<Estudiante, EstudianteDto, Integer>{

	public List<Estudiante> retornarTodo();
		
	public EstudianteDto retornar(int i) throws ModelNotFoundException, Exception;

	public EstudianteDto retornarPorNombreYApellido(String nombre, String apellido) throws ModelNotFoundException;

	public List<EstudianteDto> estudiantesNativo();

	public List<EstudianteDto> estudiantesJPQL();
	
}
