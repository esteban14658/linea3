package cundi.edu.co.demo.repository;

import cundi.edu.co.demo.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEditorialRepo extends JpaRepository<Editorial, Integer> {
}
