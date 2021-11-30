package com.travelcompany.eshop.repository;

import com.travelcompany.eshop.database.DatabaseParameters;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {

    T create(T t);

    Optional<T> read(int id);

    List<T> read();

    T update(int id, T t);

    boolean delete(int id);


    void insertDataIntoTable(Connection connection, DatabaseParameters dbParameters) throws SQLException;


    void insertIntoTableBatch(Connection connection, DatabaseParameters dbParameters, List<T> list) throws SQLException;

    //List<T> findAll();
    //List<T> findAllById(int id);


}

