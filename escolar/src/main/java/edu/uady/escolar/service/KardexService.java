package edu.uady.escolar.service;

import edu.uady.escolar.dto.KardexAlumno;
import edu.uady.escolar.dto.MateriasKardex;
import edu.uady.escolar.dto.client.LicenciaturaMateriaDTO;
import edu.uady.escolar.entity.Kardex;
import edu.uady.escolar.entity.Kardex;
import edu.uady.escolar.error.ControlEscolarException;
import edu.uady.escolar.repository.KardexRepository;
import edu.uady.escolar.repository.KardexRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class KardexService {
    @Autowired
    private Environment env;
    @Autowired
    private KardexRepository kardexRepository;

    public Kardex createKardex(Kardex kardex){
        log.info("crea Kardex: "+kardex.toString());
        return kardexRepository.save(kardex);
    }

    public Kardex updateKardex(Kardex kardex){
        log.info("actualiza Kardex: "+kardex.toString());
        return kardexRepository.save(kardex);
    }

    public List<Kardex> getAllKardexs() throws Exception{
        List<Kardex> kardexes = kardexRepository.findAll();
        if(kardexes.isEmpty()){
            throw new ControlEscolarException("no se encontraron datos");
        }
        return  kardexes;
    }

    public KardexAlumno findByKardexByAlumno(String matricula) throws Exception{
        List<Kardex> kardex = kardexRepository.findAllByAlumno_Matricula(matricula);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<LicenciaturaMateriaDTO> response = restTemplate.exchange(env.getProperty("URL_COA")+"/plan-estudios/"
                        +kardex.get(0).getAlumno().getLicenciaturaId(),
                HttpMethod.GET, entity, LicenciaturaMateriaDTO.class);
        LicenciaturaMateriaDTO ResponseDto = response.getBody();
        log.info("Consumo endpompont desde Control Escolar");
        log.info(ResponseDto);
        KardexAlumno kardexAlumno = new KardexAlumno();
        kardexAlumno.setNombreCompleto(kardex.get(0).getAlumno().getNombre()+" "+kardex.get(0).getAlumno().getApellidos());
        kardexAlumno.setFolio(kardex.get(0).getFolioKardex());
        kardexAlumno.setLicenciatrua(ResponseDto.getLicenciatura());
        List<MateriasKardex> materiasKardexes = new ArrayList<>();
        ResponseDto.getMaterias().stream().forEach(dto ->{
            MateriasKardex materiasKardex = new MateriasKardex();
            materiasKardex.setMateria(dto.getMateria());
            materiasKardex.setSemestre(dto.getSemestre());
            materiasKardex.setClaveMateria(dto.getClaveMateria());
            materiasKardexes.add(materiasKardex);
        });
        kardexAlumno.setMateriasKardex(materiasKardexes);
        return kardexAlumno;
    }

    public void deleteKardex(Long id){
        kardexRepository.deleteById(id);
    }
}
