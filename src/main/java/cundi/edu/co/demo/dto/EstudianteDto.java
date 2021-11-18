package cundi.edu.co.demo.dto;

public class EstudianteDto {

	private String cedula;
	
	private String nombre;
	
	private String apellido;

	private String correo;
	
	public EstudianteDto() {
		
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

	public String getCedula() { return cedula;	}

	public void setCedula(String cedula) { this.cedula = cedula; }

	public String getCorreo() { return correo;	}

	public void setCorreo(String correo) { this.correo = correo; }

	public EstudianteDto(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
}
