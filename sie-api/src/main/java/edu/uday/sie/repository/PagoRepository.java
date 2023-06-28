package edu.uday.sie.repository;

import edu.uday.sie.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByAlumnoIdAndMateriaId(Long AlumnoId, Long MateriaId);
}
