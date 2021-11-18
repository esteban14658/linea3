package cundi.edu.co.demo.repository;

import cundi.edu.co.demo.view.AutorView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAutorViewRepo extends JpaRepository<AutorView, Integer> {
    @Query(value = "SELECT * FROM v_autor", nativeQuery=true)
    public Page<AutorView> obtenerSinListaInterna2(Pageable paginacion);
}
