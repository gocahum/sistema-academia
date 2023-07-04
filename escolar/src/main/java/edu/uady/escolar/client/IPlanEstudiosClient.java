package edu.uady.escolar.client;

import edu.uady.escolar.dto.client.LicenciaturaMateriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "coa-api", path = "/coa-api/plan-estudios", url = "")
public interface IPlanEstudiosClient {

    @GetMapping(value = ("/{licenciatura-id}"))
    ResponseEntity<LicenciaturaMateriaDTO> findByLicenciaturaId(@PathVariable(value = "licenciatura-id") long licenciatruaId);
}
