package edu.uady.escolar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "alumnos")
@Data
@NoArgsConstructor
public class Alumno {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellidos;
    private int edad;
    private String sexo;
    private boolean documentacionCompleta;
}
