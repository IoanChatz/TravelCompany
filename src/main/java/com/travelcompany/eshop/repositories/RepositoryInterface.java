package com.travelcompany.eshop.repositories;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {

    T create(T t);
    Optional<T> read(int id);
    List<T> read();
    T update(int id, T t);
    boolean delete(int id);
}
