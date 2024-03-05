package com.me.odontologo.dao;

import java.util.List;

public interface IDao<T> {
    public List<T> buscarTodos();
    public T buscar(int id);
    public void guardar(T t);
    public void eliminar(int id);
}
