package com.me.odontologo.dto.request;

import com.me.odontologo.entity.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Integer dni;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaDeAlta;
}
