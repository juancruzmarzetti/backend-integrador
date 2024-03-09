package com.me.odontologo.services;

import com.me.odontologo.model.Odontologo;
import java.util.List;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(int id);
    List<Odontologo> buscarTodosLosOdontologos();
    Odontologo buscar(int id);
}
