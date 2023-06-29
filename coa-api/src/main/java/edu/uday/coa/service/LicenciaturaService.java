package edu.uday.coa.service;

import edu.uday.coa.dto.LicenciaturaMateriaDTO;
import edu.uday.coa.entity.Licenciatura;
import edu.uday.coa.error.COAException;
import edu.uday.coa.repository.LicenciaturaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class LicenciaturaService {
    @Autowired
    private LicenciaturaRepository licenciaturaRepository;

    public Licenciatura createLicenciatura(Licenciatura licenciatura) {
        log.info("crea Licenciatura: " + licenciatura.toString());
        return licenciaturaRepository.save(licenciatura);
    }

    public Licenciatura updateLicenciatura(Licenciatura licenciatura, String revoe) throws Exception {
        Optional<Licenciatura> optionalLicenciatura = licenciaturaRepository.findByRevoe(revoe);
        if (optionalLicenciatura.isPresent()) {
            log.info("actualiza Licenciatura: " + licenciatura.toString());
            return licenciaturaRepository.save(licenciatura);
        }
        throw new COAException("No existe la licenciatura con el revoe");
    }

    public List<Licenciatura> getAllLicenciaturas() throws Exception {
        List<Licenciatura> Licenciaturaes = licenciaturaRepository.findAll();
        if (Licenciaturaes.isEmpty()) {
            throw new COAException("no se encontraron datos");
        }
        return Licenciaturaes;
    }



    public void deleteLicenciatura(Long id) {
        licenciaturaRepository.deleteById(id);
    }
}
