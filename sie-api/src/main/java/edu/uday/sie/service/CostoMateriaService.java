package edu.uday.sie.service;

import edu.uday.sie.entity.CostoMateria;
import edu.uday.sie.error.COAException;
import edu.uday.sie.repository.CostoMateriaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CostoMateriaService {

    @Autowired
    private CostoMateriaRepository costoMateriaRepository;

    public List<CostoMateria> getAllCostoMaterias() throws Exception {
        log.info("getAllCostoMaterias");
        List<CostoMateria> costoMateriaList = costoMateriaRepository.findAll();
        log.info("obtubo todas las materias");
        if (costoMateriaList.isEmpty()) {
            throw new COAException("No se encontraron datos");
        }

        return costoMateriaList;
    }

    public CostoMateria createCostoMateria(CostoMateria costoMateria) {
        log.info("Se crea costo materia: " + costoMateria.toString());
        return costoMateriaRepository.save(costoMateria);
    }

    public CostoMateria updateCostoMateria(CostoMateria costoMateria) throws Exception {
        Optional<CostoMateria> costoMateriaOptional = costoMateriaRepository.findCostoMateriaById(costoMateria.getMateriaId());

        if (costoMateriaOptional.isPresent()) {
            log.info("Actualizando costo materia: " + costoMateria.toString());
            return costoMateriaRepository.save(costoMateria);
        }

        throw new COAException("No se encuentra el costo de la materia: " + costoMateria.toString());
    }

    public void deleteCostoMateria(Long id) {
        costoMateriaRepository.deleteById(id);
    }

}
