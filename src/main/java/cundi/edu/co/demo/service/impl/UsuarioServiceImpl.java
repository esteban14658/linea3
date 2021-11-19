package cundi.edu.co.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cundi.edu.co.demo.dto.UsuarioDto;
import cundi.edu.co.demo.entity.Usuario;
import cundi.edu.co.demo.exception.ArgumentRequiredException;
import cundi.edu.co.demo.exception.ConflictException;
import cundi.edu.co.demo.exception.ModelNotFoundException;
import cundi.edu.co.demo.repository.IUsuarioRepo;
import cundi.edu.co.demo.service.IUsuarioService;
import org.springframework.security.core.GrantedAuthority;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioRepo repo;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = repo.findOneByNick(username);		
		if(usuario == null)
			new UsernameNotFoundException("----Usuario no encontrado");

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));

		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);
		return ud;
	}



	@Override
	public Page<Usuario> retornarPaginado(int page, int size) {
		
		Page<Usuario> paginaUsuarios = repo.findAll(PageRequest.of(page, size));
		return paginaUsuarios;
	}



	@Override
	public Page<Usuario> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public UsuarioDto retonarPorId(Integer id) throws ModelNotFoundException {
		Usuario usuario = repo.findById(id).get();
		UsuarioDto uDto = new UsuarioDto();
		
		if(usuario != null) {
			ModelMapper modelMapper = new ModelMapper();
			uDto = modelMapper.map(usuario, UsuarioDto.class);
		}else {
			throw new ModelNotFoundException("El usuario con el id " + id + " no existe");
		}
		
		
		return uDto;
	}
	
	

	@Override
	public void guardar(Usuario obj) throws ConflictException {
		if(!repo.existsByNickOrDocumento(obj.getNick(), obj.getDocumento())){
			obj.setClave(bcrypt.encode(obj.getClave()));
			repo.save(obj);
		}else {
			throw new ConflictException("El Usuario con el nick " + obj.getNick() + " o el documento "
					+ obj.getDocumento() + " ya existe."); 
		}

	}



	@Override
	public void editar(Usuario obj) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		if(obj.getNick().isBlank())
			throw new ArgumentRequiredException("El usuario necesita un nick");
		
		if(obj.getDocumento().isBlank())
			throw new ArgumentRequiredException("El usuario necesita un documento");
		
		Usuario usuario = repo.findOneByNick(obj.getNick());
		
		if(usuario != null) {
			if(usuario.getDocumento().equals(obj.getDocumento())) {
				repo.save(obj);
			}else {
				throw new ConflictException("El nick ya esta en uso");
			}
		}else {
			if(!repo.existsByDocumento(obj.getDocumento()))
				throw new ModelNotFoundException("El usuario con el documento " + obj.getDocumento() 
				+ " no existe");
			
			repo.save(obj);
		}

	}



	@Override
	public void eliminar(int obj) throws ModelNotFoundException {
		if(!repo.existsById(obj))
			throw new ModelNotFoundException("El usuario con el id " + obj + " no existe");
		
		repo.deleteById(obj);

	}

}
