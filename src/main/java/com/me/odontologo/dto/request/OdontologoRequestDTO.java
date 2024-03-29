package com.me.odontologo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OdontologoRequestDTO {
    private Integer matricula;
    private String nombre;
    private String apellido;
}
