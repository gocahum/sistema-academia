package edu.uday.coa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name ="licenciaturas")
@Data
@NoArgsConstructor
public class Licenciatura {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licenciatura_seq")
    @SequenceGenerator(name = "licenciatura_seq", sequenceName = "licenciatura_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name ="revoe")
    private String revoe;
    @Column(name  ="nombre")
    private String nombre;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "licenciatura")
    @ToString.Exclude
    @JsonIgnore
    private List<PlanEstudio> planEstudios;
}
