package com.unknowbrain.ubpetclinic.services;

import java.util.List;

public interface CrudService<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
