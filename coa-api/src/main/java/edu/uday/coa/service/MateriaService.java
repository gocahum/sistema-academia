package edu.uday.coa.service;

import edu.uday.coa.entity.Materia;
import edu.uday.coa.error.COAException;
import edu.uday.coa.repository.MateriaRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

    public Materia createMateria(Materia materia){
        log.info("crea Materia: "+materia.toString());
        return materiaRepository.save(materia);
    }

    public Materia updateMateria(Materia Materia){
        log.info("actualiza Materia: "+Materia.toString());
        return materiaRepository.save(Materia);
    }

    public List<Materia> getAllMaterias() throws Exception{
        List<Materia> materiaes = materiaRepository.findAll();
        if(materiaes.isEmpty()){
            throw new COAException("no se encontraron datos");
        }
        return  materiaes;
    }

    public void deleteMateria(Long id){
        materiaRepository.deleteById(id);
    }
}
