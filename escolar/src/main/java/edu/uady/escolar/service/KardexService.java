package edu.uady.escolar.service;

import edu.uady.escolar.entity.Kardex;
import edu.uady.escolar.entity.Kardex;
import edu.uady.escolar.error.ControlEscolarException;
import edu.uady.escolar.repository.KardexRepository;
import edu.uady.escolar.repository.KardexRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class KardexService {
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

    public void deleteKardex(Long id){
        kardexRepository.deleteById(id);
    }
}
