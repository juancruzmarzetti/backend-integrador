package com.me.odontologo.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> buscarTodos();
    T buscar(int id);
    T guardar(T t);
    void eliminar(int id);
}
