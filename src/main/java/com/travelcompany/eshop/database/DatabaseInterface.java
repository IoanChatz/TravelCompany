package com.travelcompany.eshop.database;

import java.sql.Connection;
import java.sql.SQLException;


public interface DatabaseInterface {
    public Connection   getConnection(DatabaseParameters dbParameters) throws SQLException;
   /* public void         selectRowsFromATableAndPrintToWeb(Connection connection, String sql.properties, PrintWriter out) throws SQLException;
    public int          insertRowsToATable(Connection connection, String sql.properties) throws SQLException;
    public int          insertRowsToATable(Connection connection, String tableName, List<String> FieldNames, List<Product> FieldValues) throws SQLException;
    public int          updateRowsOfATable(Connection connection, String sql.properties) throws SQLException;
    public int          deleteRowsFromATable(Connection connection, String sql.properties) throws SQLException;
    public int          deleteRowsFromATable(Connection connection, String sql.properties, List<String> fieldValues) throws SQLException;*/
}
