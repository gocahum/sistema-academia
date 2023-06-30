package edu.uady.escolar.service;

import edu.uady.escolar.dto.KardexAlumno;
import edu.uady.escolar.dto.client.LicenciaturaMateriaDTO;
import edu.uady.escolar.dto.client.MateriaDTO;
import edu.uady.escolar.entity.Alumno;
import edu.uady.escolar.entity.Kardex;
import edu.uady.escolar.error.ControlEscolarException;
import edu.uady.escolar.repository.AlumnoRepository;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
	private KardexService kardexService = new KardexService();

    public Alumno createAlumno(Alumno alumno) throws Exception {
        log.info("crea alumno: "+alumno.toString());
        Optional<Alumno> existeAlumno = alumnoRepository.findByMatricula(alumno.getMatricula());
        List<Kardex> kardexList = new ArrayList<>();
        if(!existeAlumno.isPresent()) {
        	RestTemplate restTemplate = new RestTemplate();
        	HttpHeaders headers = new HttpHeaders();
        	HttpEntity entity = new HttpEntity(headers);
        	ResponseEntity<LicenciaturaMateriaDTO> response = restTemplate.exchange("http://localhost:8090/coa-api/plan-estudios/"+alumno.getLicenciaturaId(),
        			HttpMethod.GET, entity, LicenciaturaMateriaDTO.class);
        	LicenciaturaMateriaDTO responseDto = response.getBody();
        	if(!responseDto.getLicenciatura().isEmpty()) {
        		log.info("Consumo enpoint desde Control Escolar al crear un alumno");
        		log.info(responseDto);
        		
        		List<MateriaDTO> materiasDto = responseDto.getMaterias();
        		materiasDto.stream().forEach(m ->{
        			Kardex kardex = new Kardex();
        			int random = (int) (Math.random() * (1000 - 100 + 1) + 100);
        			kardex.setAlumno(alumno);
        			kardex.setClaveMateria(m.getClaveMateria());
        			kardex.setCalificacion(0.0);
        			kardex.setFolioKardex("FM-"+random);
        			kardexList.add(kardex);
        		});
        		log.info("Inserta alumno: "+alumno.toString());
        		
            	Alumno alumnoResult = alumnoRepository.save(alumno);
            	//Tener esto en el service de kardex
            	kardexList.stream().forEach(k -> {
            		kardexService.createKardex(k);
            	});
            	return alumnoResult;
        	}
        	
        }
        throw new ControlEscolarException("Ya existe un alumno con esa matricula");
    }

    public Alumno updateAlumno(Alumno alumno){
        log.info("actualiza alumno: "+alumno.toString());
        return alumnoRepository.save(alumno);
    }

    public List<Alumno> getAllAlumnos(){
        return alumnoRepository.findAll();
    }

    public void deleteAlumno(Long id){
        alumnoRepository.deleteById(id);
    }
}
