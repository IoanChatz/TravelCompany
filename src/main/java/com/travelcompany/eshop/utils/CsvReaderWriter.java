package com.travelcompany.eshop.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReaderWriter {

    private void  readCsvFiles() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Directory.FILE_DIRECTORY + "customers.csv"));
        String row = bufferedReader.readLine();
        while (row != null) {
            String[] data = row.split(", ");
            System.out.println();
        }
        bufferedReader.close();
    }
}
