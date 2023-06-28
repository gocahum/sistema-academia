package edu.uday.sie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
public class Pago {
    @Id
    @Column(name = "pago_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "alumno_id", nullable = false, precision = 30)
    private Long alumnoId;

    @Column(name = "materia_id", nullable = false, precision = 30)
    private Long materiaId;

    @Column(name = "fecha_pago", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaPago;

    @Column(name = "importe_pago", nullable = false)
    private Double importePago;

}
