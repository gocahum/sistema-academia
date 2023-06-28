package edu.uday.coa.repository;

import edu.uday.coa.entity.PlanEstudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanEstudioRepository extends JpaRepository<PlanEstudio, Long> {
    Optional<PlanEstudio> findByLicenciatura_RevoeAndAndMateriaClaveMateria(String revoe, String materia);
}
