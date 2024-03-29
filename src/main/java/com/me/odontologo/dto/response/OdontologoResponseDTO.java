package com.me.odontologo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OdontologoResponseDTO {
    private Long id;
    private Integer matricula;
    private String nombre;
    private String apellido;
}
