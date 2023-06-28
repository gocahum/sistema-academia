package edu.uday.coa.controller;

import edu.uday.coa.entity.Materia;
import edu.uday.coa.error.COAException;
import edu.uday.coa.service.MateriaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/materia")
@Log4j2
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<?> getAllMateria() {
        try {
            return ResponseEntity.ok().body(materiaService.getAllMaterias());
        }catch (COAException ex){
            log.warn("Sin datos");
            log.error(ex);
            return new ResponseEntity<>("datos no encontrados", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public Materia createMateria(@RequestBody Materia materia){
        log.info("Kardex  a guardar: "+materia.toString());
        return materiaService.createMateria(materia);
    }

    @PutMapping
    public Materia updateMateria(@RequestBody Materia materia) {
        log.info("Kardex a actualizar :"+materia.toString());
        return materiaService.updateMateria(materia);
    }

    @DeleteMapping("/{id}")
    public void deleteMateria(@PathVariable (value = "id") Long id){
         materiaService.deleteMateria(id);
    }
}
