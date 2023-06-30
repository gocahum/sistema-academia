package edu.uady.escolar.controller;

import edu.uady.escolar.entity.Alumno;
import edu.uady.escolar.error.ControlEscolarException;
import edu.uady.escolar.service.AlumnoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alumno")
@Log4j2
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> getAllAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @PostMapping
    public ResponseEntity createAlumno(@RequestBody Alumno alumno){
    	try {
    		 log.info("Alumno  a guardar: "+alumno.toString());
    	     return new ResponseEntity<>(alumnoService.createAlumno(alumno), HttpStatus.CREATED);
    	} catch (ControlEscolarException ex) {
    		log.warn("Sin datos");
    		log.error(ex);
    		return new ResponseEntity<>("alumno no creado", HttpStatus.BAD_REQUEST);
    	} catch (Exception e) {
    		log.error(e);
    		throw new RuntimeException(e);
    	}
       
    }

    @PutMapping
    public Alumno updateAlumno(@RequestBody Alumno alumno) {
        log.info("Alumno a actualizar :"+alumno.toString());
        return alumnoService.updateAlumno(alumno);
    }

    @DeleteMapping("/{id}")
    public void deletealumno(@PathVariable (value = "id") Long id){
         alumnoService.deleteAlumno(id);
    }
}
