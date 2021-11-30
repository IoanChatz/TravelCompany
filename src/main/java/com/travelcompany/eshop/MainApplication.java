package com.travelcompany.eshop;
import com.travelcompany.eshop.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;



public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);


    public static void main(String[] args) throws SQLException {

        new Menu().runMenu();
    }
}

