package cundi.edu.co.demo.dto;

import cundi.edu.co.demo.entity.Rol;

public class UsuarioDto {
	
	private String documento;
	
	private String nombre;
	
	private String apellido;
	
	private String nick;
	
	private Rol rol;

	public UsuarioDto() {
		
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
