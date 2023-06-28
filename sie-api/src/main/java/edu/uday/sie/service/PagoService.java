package edu.uday.sie.service;

import edu.uday.sie.entity.Pago;
import edu.uday.sie.error.COAException;
import edu.uday.sie.repository.PagoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> getAllPagos() throws Exception {
        List<Pago> pagoList = pagoRepository.findAll();

        if (pagoList.isEmpty()) {
            throw new COAException("No se encontraron datos");
        }

        return pagoList;
    }

    public Pago createPago(Pago pago) {
        log.info("Se crea pago: " + pago.toString());
        return pagoRepository.save(pago);
    }

    public Pago updatePago(Pago pago) throws Exception {
        Optional<Pago> pagoOptional = pagoRepository.findByAlumnoIdAndMateriaId(pago.getAlumnoId(), pago.getMateriaId());

        if (pagoOptional.isPresent()) {
            log.info("Actualizando pago: " + pago.toString());
            return pagoRepository.save(pago);
        }

        throw new COAException("No se encontró el pago: " + pago.toString());
    }

    public void deletePago(Long id) {
        pagoRepository.deleteById(id);
    }
}
