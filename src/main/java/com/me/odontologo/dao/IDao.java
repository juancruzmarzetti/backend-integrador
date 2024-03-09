package com.me.odontologo.dao;

import java.util.List;

public interface IDao<T> {
    List<T> buscarTodos();
    T buscar(int id);
    T guardar(T t);
    void eliminar(int id);
}
