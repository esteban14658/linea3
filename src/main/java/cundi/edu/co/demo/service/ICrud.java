package cundi.edu.co.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;

public interface ICrud<T, D, ID> {
	
	public Page<T> retornarPaginado(int page, int size);
	
	public Page<T> retornarPaginado(Pageable page);
	
	public D retonarPorId(ID id) throws ModelNotFoundException;
		
	public void guardar(T t)  throws ConflictException, ModelNotFoundException, ArgumentRequiredException;
	
	public void editar(T t)  throws ArgumentRequiredException, ModelNotFoundException, ConflictException;
	
	public void eliminar(int id) throws ModelNotFoundException, ConflictException;

}
