package com.travelcompany.eshop.database;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DatabaseParameters {


    private String DB_CONNECTION_URL;
    private String HOST;
    private String HOST_PORT;
    private String DATABASE;
    private String DB_USERNAME;
    private String DB_PASSWORD;

    public DatabaseParameters() {
        DB_CONNECTION_URL = "jdbc:mysql://";
        HOST = "localhost";
        HOST_PORT = "3306";
        DATABASE = "travelcompany";
        DB_USERNAME = "root";
        DB_PASSWORD = "root";
    }
}
