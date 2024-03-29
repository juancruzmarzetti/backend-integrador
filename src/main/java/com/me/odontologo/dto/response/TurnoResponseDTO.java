package com.me.odontologo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoResponseDTO {
    private Long id;
    private Long odontologo;
    private Long paciente;
    private LocalDate fecha;
    private LocalTime hora;
}
