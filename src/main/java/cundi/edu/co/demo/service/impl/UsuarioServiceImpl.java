package cundi.edu.co.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Page<Usuario> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public UsuarioDto retonarPorId(Integer id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public void guardar(Usuario obj) throws ConflictException {
		// TODO Auto-generated method stub

	}



	@Override
	public void editar(Usuario obj) throws ArgumentRequiredException, ModelNotFoundException, ConflictException {
		// TODO Auto-generated method stub

	}



	@Override
	public void eliminar(int obj) throws ModelNotFoundException {
		// TODO Auto-generated method stub

	}

}
