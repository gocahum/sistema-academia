package edu.uday.coa.service;

import edu.uday.coa.dto.LicenciaturaMateriaDTO;
import edu.uday.coa.dto.MateriaDTO;
import edu.uday.coa.entity.PlanEstudio;
import edu.uday.coa.error.COAException;
import edu.uday.coa.repository.PlanEstudioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PlanEstudiosService {
    @Autowired
    private PlanEstudioRepository planEstudioRepository;

    public PlanEstudio createPlanEstudio(PlanEstudio planEstudio) throws Exception{
        log.info("crea PlanEstudio: "+planEstudio.toString());
        Optional<PlanEstudio> existePlanEstudios = planEstudioRepository
                .findByLicenciatura_RevoeAndAndMateriaClaveMateria(planEstudio.getLicenciatura().getRevoe(),
                        planEstudio.getMateria().getClaveMateria());
        if(!existePlanEstudios.isPresent()){
            log.info("inserta PlanEstudio: "+planEstudio.toString());
            return planEstudioRepository.save(planEstudio);
        }
        throw new COAException("Ya esxiste una materia asignada a esta licenciatura");
    }

    public PlanEstudio updatePlanEstudio(PlanEstudio planEstudio)throws Exception{
        if(planEstudio.getId() == null){
            throw new COAException("no se puede actualizar este plan de estudios");
        }
        Optional<PlanEstudio> existePlanEstudios = planEstudioRepository
                .findByLicenciatura_RevoeAndAndMateriaClaveMateria(planEstudio.getLicenciatura().getRevoe(),
                        planEstudio.getMateria().getClaveMateria());
        if(existePlanEstudios.isPresent()){
            log.info("actualiza PlanEstudio: "+planEstudio.toString());
            return planEstudioRepository.save(planEstudio);
        }
        throw new COAException("No existe una materia asignada a esta licenciatura");
    }

    public List<PlanEstudio> getAllPlanEstudios() throws Exception{
        List<PlanEstudio> planEstudioes = planEstudioRepository.findAll();
        if(planEstudioes.isEmpty()){
            throw new COAException("no se encontraron datos");
        }
        return  planEstudioes;
    }

    public LicenciaturaMateriaDTO getLicienciatruaMaterias (Long licenciaturaId)throws  Exception{
        List<PlanEstudio> planEstudios = planEstudioRepository.findPlanEstudioByLicenciatura_Id(licenciaturaId);
        log.info(planEstudios.toString());
        LicenciaturaMateriaDTO dto = new LicenciaturaMateriaDTO();
        dto.setLicenciatura(planEstudios.get(0).getLicenciatura().getNombre());
        List<MateriaDTO> materiasDto = new ArrayList<>();
        planEstudios.stream().forEach(pe ->{
            MateriaDTO materia = new MateriaDTO();
            materia.setClaveMateria(pe.getMateria().getClaveMateria());
            materia.setMateria(pe.getMateria().getNombreMateria());
            materia.setSemestre(pe.getSemestre());
            materia.setCreditos(pe.getCreditos());
            materiasDto.add(materia);
        });
        dto.setMaterias(materiasDto);
        return dto;
    }

    public void deletePlanEstudio(Long id){
        planEstudioRepository.deleteById(id);
    }
}
