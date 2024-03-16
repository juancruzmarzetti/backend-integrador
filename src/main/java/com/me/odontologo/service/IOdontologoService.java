package com.me.odontologo.service;

import com.me.odontologo.entity.Odontologo;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
    List<Odontologo> buscarTodosLosOdontologos();
    Optional<Odontologo> buscar(Long id);
}
