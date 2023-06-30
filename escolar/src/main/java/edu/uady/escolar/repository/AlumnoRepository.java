package edu.uady.escolar.repository;

import edu.uady.escolar.entity.Alumno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
	Optional<Alumno> findByMatricula(String matricula);
}
