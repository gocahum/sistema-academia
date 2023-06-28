package edu.uday.coa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plan_estudios")
@Data
@NoArgsConstructor
public class PlanEstudio {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_estudios_seq")
    @SequenceGenerator(name = "plan_estudios_seq", sequenceName = "plan_estudios_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "licenciatura_id")
    private Licenciatura licenciatura;
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
    @Column(name = "semestre")
    private Integer semestre;
    @Column(name = "creditos")
    private Integer creditos;
}
