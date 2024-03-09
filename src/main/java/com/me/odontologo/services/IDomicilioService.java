package com.me.odontologo.services;

import com.me.odontologo.domain.Domicilio;
import java.util.List;

public interface IDomicilioService {
    Domicilio guardarDomicilio(Domicilio domicilio);
    List<Domicilio> buscarTodosLosDomicilios();
    void eliminarDomicilio(int id);
    Domicilio buscarDomicilio(int id);
}
