package edu.uday.coa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LicenciaturaMateriaDTO {
    private String licenciatura;

    private List<MateriaDTO> materias;
}
