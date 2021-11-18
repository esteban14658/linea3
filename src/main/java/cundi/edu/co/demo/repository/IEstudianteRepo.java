package cundi.edu.co.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Estudiante;

import java.util.List;

@Repository
public interface IEstudianteRepo extends JpaRepository<Estudiante, Integer> {

	public Estudiante findByCedula(String cedula);
	
	public Estudiante findByCorreo(String correo);

	public Estudiante findByNombreAndApellido(String nombre, String apellido);

	public Estudiante findByNombreOrApellido(String nombre, String apellido);
	
	public Boolean existsByCedula(String cedula);
	
	public Boolean existsByCorreo(String correo);
	
	//JPQL
	@Query(value = "SELECT e FROM Estudiante e", nativeQuery=false)
	public List<Estudiante> estudiantesJPQL();
	//SQL
	@Query(value = "SELECT * FROM estudiante", nativeQuery=true)
	public List<Estudiante> estudiantesNativo();


	
	//correo existe sin tener en cuenta el propio
	
	// findBy todas las opciones
	
}
