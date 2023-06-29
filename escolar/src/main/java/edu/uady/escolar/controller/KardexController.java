package edu.uady.escolar.controller;

import edu.uady.escolar.entity.Kardex;
import edu.uady.escolar.error.ControlEscolarException;
import edu.uady.escolar.service.KardexService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/kardex")
@Log4j2
public class KardexController {
    @Autowired
    private KardexService kardexService;

    @GetMapping
    public ResponseEntity<?> getAllKardexs() {
        try {
            return ResponseEntity.ok().body(kardexService.getAllKardexs());
        }catch (ControlEscolarException ex){
            log.warn("Sin datos");
            log.error(ex);
            return new ResponseEntity<>("datos no encontrados", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/{matricula}")
    public ResponseEntity<?> findByKardexByAlumno(@PathVariable("matricula") String matricula) {
        try {
            return ResponseEntity.ok().body(kardexService.findByKardexByAlumno(matricula));
        }catch (ControlEscolarException ex){
            log.warn("Sin datos");
            log.error(ex);
            return new ResponseEntity<>("datos no encontrados", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @PostMapping
    public Kardex createKardex(@RequestBody Kardex kardex){
        log.info("Kardex  a guardar: "+kardex.toString());
        return kardexService.createKardex(kardex);
    }

    @PutMapping
    public Kardex updateKardex(@RequestBody Kardex kardex) {
        log.info("Kardex a actualizar :"+kardex.toString());
        return kardexService.updateKardex(kardex);
    }

    @DeleteMapping("/{id}")
    public void deleteKardex(@PathVariable (value = "id") Long id){
         kardexService.deleteKardex(id);
    }
}
