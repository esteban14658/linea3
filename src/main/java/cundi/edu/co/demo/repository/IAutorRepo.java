package cundi.edu.co.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cundi.edu.co.demo.entity.Autor;

@Repository
public interface IAutorRepo extends JpaRepository<Autor, Integer>{

    @Query(value = "SELECT * FROM autor", nativeQuery=true)
    public Page<Autor> obtenerSinListaInterna(Pageable paginacion);

    @Query(value = "SELECT * FROM autor WHERE id = :id", nativeQuery=true)
    public Autor findByIdAutor(@Param("id")Integer id);

    public Boolean existsByCorreo(String correo);

    public Boolean existsByCedula(String cedula);

    @Query(value = "SELECT COUNT(*) FROM autor WHERE NOT id = :id AND correo = :correo", nativeQuery=true)
    public Integer contarDiferentes(@Param("id")Integer id, @Param("correo")String correo);
}
