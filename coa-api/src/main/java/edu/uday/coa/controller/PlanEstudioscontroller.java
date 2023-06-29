package edu.uday.coa.controller;

import edu.uday.coa.entity.PlanEstudio;
import edu.uday.coa.error.COAException;
import edu.uday.coa.service.PlanEstudiosService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan-estudios")
@Log4j2
public class PlanEstudioscontroller {
    @Autowired
    private PlanEstudiosService planEstudiosService;

    @GetMapping
    public ResponseEntity<?> findAllPlanEstudios(){
        try {
            return ResponseEntity.ok().body(planEstudiosService.getAllPlanEstudios());
        }catch (COAException ex){
            log.warn("Sin datos");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = ("/{licenciatura-id}"))
    public ResponseEntity<?> findByLicenciaturaId(@PathVariable(value = "licenciatura-id") long licenciatruaId){
        try {
            return ResponseEntity.ok().body(planEstudiosService.getLicienciatruaMaterias(licenciatruaId));
        }catch (COAException ex){
            log.warn("Sin datos");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity createPlanEstudios(@RequestBody PlanEstudio planEstudio){
        try {
            return new ResponseEntity<>(planEstudiosService.createPlanEstudio(planEstudio), HttpStatus.CREATED);
        }catch (COAException ex){
            log.warn("Sin datos");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity updatePlanEstudios(@RequestBody PlanEstudio planEstudio){
        try {
            return new ResponseEntity<>(planEstudiosService.updatePlanEstudio(planEstudio), HttpStatus.CREATED);
        }catch (COAException ex){
            log.warn("Sin datos");
            log.error(ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
