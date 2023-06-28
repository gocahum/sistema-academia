package edu.uday.sie.repository;

import edu.uday.sie.entity.CostoMateria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostoMateriaRepository extends JpaRepository<CostoMateria, Long> {

    Optional<CostoMateria> findCostoMateriaById(Long id);
}
