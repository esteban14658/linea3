package cundi.edu.co.demo.repository;

import cundi.edu.co.demo.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepo extends JpaRepository<Libro, Integer> {

    public Boolean existsByAutor(Integer idAutor);

}
