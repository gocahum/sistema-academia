package edu.uady.escolar.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MateriasKardex {
    private String claveMateria;
    private String materia;
    private int semestre;
    private double calificacion;
}
