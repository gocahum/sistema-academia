package edu.uady.escolar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "alumnos")
@Data
@NoArgsConstructor
public class Alumno {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="matricula")
    private String matricula;
    private String nombre;
    private String apellidos;
    private LocalDate edad;
    @Column(name = "sexo", precision = 1, length = 1)
    private char sexo;
    @Column(name = "licenciatura_id")
    private Long licenciaturaId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "alumno")
    @JsonIgnore
    private List<Kardex> kardexs;

}
