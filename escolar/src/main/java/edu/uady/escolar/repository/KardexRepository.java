package edu.uady.escolar.repository;

import edu.uady.escolar.entity.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KardexRepository extends JpaRepository<Kardex, Long> {
}
