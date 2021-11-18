package cundi.edu.co.demo.service.impl;

import cundi.edu.co.demo.dto.AutorDto;
import cundi.edu.co.demo.entity.Libro;
import cundi.edu.co.demo.repository.IAutorViewRepo;
import cundi.edu.co.demo.view.AutorView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.entity.Autor;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IAutorRepo;
import cundi.edu.co.demo.service.IAutorService;

import java.util.List;

@Service
public class AutorServiceImpl implements IAutorService {
	
	@Autowired
	private IAutorRepo repo;

	@Autowired
	private IAutorViewRepo viewRepo;

	private ModelMapper mapper;

	public AutorServiceImpl(ModelMapper mapper){
		this.mapper = mapper;
	}

	@Override
	public Page<Autor> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Autor> retornarPaginado(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Page<AutorDto> obtenerSinListaInterna(Pageable page){
		Page<Autor> resultado = repo.findAll(page);
		/*List<AutorDto> listaAutorDto = new ArrayList<>();
		for (Autor item: resultado.getContent()) {
			AutorDto autorDto = mapToDTO(item);
			listaAutorDto.add(autorDto);
		}
		Page<AutorDto> envio = new PageImpl<>(listaAutorDto, page, listaAutorDto.size());
		return envio;*/
		final Page<AutorDto> autorDtoPage = resultado.map(this::convertToAutorDto);
		return autorDtoPage;
	}

	@Override
	public Page<AutorView> obtenerSinListaInterna2(Pageable page) {
		Page<AutorView> resultado = viewRepo.obtenerSinListaInterna2(page);
		return resultado;
	}

	@Override
	public List<Autor> traerTodo() {
		List<Autor> list = repo.findAll();
		return list;
	}

	@Override
	public AutorDto retonarPorId(Integer idAutor) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		Boolean existe = repo.existsById(idAutor);
		if (existe == false){
			throw new ModelNotFoundException("No existe el autor");
		}
		Autor autor = repo.findById(idAutor).get();
		AutorDto autorDto = mapToDTO(autor);
		return autorDto;
	}

	@Override
	public void guardar(Autor autor) throws ConflictException, ModelNotFoundException {
		Boolean existeCorreo = repo.existsByCorreo(autor.getCorreo());
		Boolean existeCedula = repo.existsByCedula(autor.getCedula());
		if (existeCorreo == true){
			throw new ConflictException("El correo " + autor.getCorreo() + " ya ha sido registrado");
		}
		if (existeCedula == true){
			throw new ConflictException("La cedula " + autor.getCedula() + " ya ha sido registrada");
		}
		if (autor.getLibro().size() > 0) {
			for (Libro item : autor.getLibro()) {
				item.setAutor(autor);
				System.out.println("Entro al for each");
			}
		}
		repo.save(autor);
	}

	@Override
	public void editar(Autor autor) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		if(autor.getId() != null) {
			if(validarExistenciaPorId(autor.getId())) {

				Autor autorAux = this.repo.findById(autor.getId()).get();
				autor.setCedula(autorAux.getCedula());

				if(autor.getCorreo().equals(autorAux.getCorreo()))
					this.repo.save(autor);
				else {
					if(!repo.existsByCorreo(autor.getCorreo())) {
						this.repo.save(autor);
					} else {
						throw new ConflictException("Correo ya existe");
					}
				}

			} else
				throw new ModelNotFoundException("Autor no encontrado");
		} else {
			throw new ArgumentRequiredException("IdAutor es requerido");
		}
	}

	@Override
	public void eliminar(int idAutor) throws ModelNotFoundException {
		if(validarExistenciaPorId(idAutor)){
			this.repo.deleteById(idAutor);
		}
		else {
			throw new ModelNotFoundException("El autor con el id " + idAutor + " no fue encontrado");
		}
	}

	private AutorDto mapToDTO(Autor autor){
		AutorDto autorDto = mapper.map(autor, AutorDto.class);
		return autorDto;
	}

	private Boolean validarExistenciaPorId(int idAutor) {
		return repo.existsById(idAutor);
	}

	private AutorDto convertToAutorDto(final Autor autor) {
		final AutorDto autorDto = new AutorDto();
		autorDto.setCedula(autor.getCedula());
		autorDto.setNombre(autor.getNombre());
		autorDto.setApellido(autor.getApellido());
		autorDto.setCorreo(autor.getCorreo());
		return autorDto;
	}

}
