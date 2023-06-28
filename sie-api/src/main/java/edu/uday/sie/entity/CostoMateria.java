package edu.uday.sie.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "costo_materias")
@Data
@NoArgsConstructor
public class CostoMateria {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "materia_id", nullable = false, precision = 30, unique = true)
    private Long materiaId;

    @Column(name = "costo", nullable = false)
    private Double costo;
}
