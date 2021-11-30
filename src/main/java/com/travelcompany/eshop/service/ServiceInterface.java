package com.travelcompany.eshop.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {

    List<T> getAll();
    void add(T t);
    void delete(int id);
    void update(T t);
    Optional<T> getById(int id);

}
