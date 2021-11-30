package com.travelcompany.eshop.util;

import com.travelcompany.eshop.model.Directory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TxtWriter {
    public void writeTxtFile(List<String> dataList, String filename) {
        try (FileWriter fileWriter = new FileWriter(Directory.REPORTS_DIRECTORY + filename)) {
            for (String data : dataList) {
                fileWriter.write(data + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
