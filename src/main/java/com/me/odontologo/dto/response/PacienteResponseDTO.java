package com.me.odontologo.dto.response;

import com.me.odontologo.entity.Domicilio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class PacienteResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Integer dni;
    private LocalDate fechaDeAlta;
    private String usuario;
}
