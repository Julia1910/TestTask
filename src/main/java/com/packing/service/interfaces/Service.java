package com.packing.service.interfaces;

import java.util.List;

public interface Service<T> {

    void add(T entity);

    List<T> getAll();

    T findById(Integer id);

    void remove(Integer id);
}
