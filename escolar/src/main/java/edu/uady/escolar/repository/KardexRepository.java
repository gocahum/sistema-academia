package edu.uady.escolar.repository;

import edu.uady.escolar.entity.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KardexRepository extends JpaRepository<Kardex, Long> {


    List<Kardex> findAllByAlumno_Matricula(String matricula);
}
