package com.me.odontologo.service;

import com.me.odontologo.entity.Odontologo;
import java.util.List;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(int id);
    List<Odontologo> buscarTodosLosOdontologos();
    Odontologo buscar(int id);
}
