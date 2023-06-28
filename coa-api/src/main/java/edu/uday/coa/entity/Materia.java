package edu.uday.coa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "materias")
@Data
@NoArgsConstructor
public class Materia {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materia_seq")
    @SequenceGenerator(name = "materia_seq", sequenceName = "materia_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "clave_materia")
    private String claveMateria;
    @Column(name ="nombre")
    private String nombreMateria;
    @JsonIgnore
    @JsonProperty(value = "OtroNombreDelPlanDeEstudios")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "materia")
    @ToString.Exclude
    private List<PlanEstudio> planEstudios;
}
