package edu.uday.sie.controller;

import edu.uday.sie.entity.Pago;
import edu.uday.sie.error.COAException;
import edu.uday.sie.service.PagoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pago")
@Log4j2
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<?> getAllPagos() {
        try {
            return ResponseEntity.ok().body(pagoService.getAllPagos());
        } catch (COAException coaException) {
            log.warn("Sin datos");
            log.error(coaException);
            return new ResponseEntity<>("Datos no encontrados", HttpStatus.OK);
        } catch (Exception exception) {
            log.error(exception);
            throw new RuntimeException(exception);
        }
    }

    @PostMapping
    public Pago createPago(@RequestBody Pago pago) {
        return pagoService.createPago(pago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePago(@RequestBody Pago pago) {
        try {
            return ResponseEntity.ok().body(pagoService.updatePago(pago));
        } catch (COAException coaException) {
            log.warn("Sin datos");
            log.error(coaException);
            return new ResponseEntity<>(coaException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            log.error(exception);
            throw new RuntimeException(exception);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePago(@PathVariable(value = "id") Long id) {
        pagoService.deletePago(id);
    }

}
