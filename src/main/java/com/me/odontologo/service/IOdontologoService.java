package com.me.odontologo.service;

import com.me.odontologo.dto.request.OdontologoRequestDTO;
import com.me.odontologo.dto.response.OdontologoResponseDTO;
import com.me.odontologo.entity.Odontologo;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    OdontologoResponseDTO guardarOdontologo(OdontologoRequestDTO odontologoRequest);
    void eliminarOdontologo(Long id);
    List<OdontologoResponseDTO> buscarTodosLosOdontologos();
    OdontologoResponseDTO buscar(Long id);
    OdontologoResponseDTO actualizarOdontologo(Odontologo odontologo);
}
