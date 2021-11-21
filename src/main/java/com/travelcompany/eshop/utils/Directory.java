package com.travelcompany.eshop.utils;

import java.io.File;

public enum Directory {

    FILE_DIRECTORY(System.getProperty("user.home") + File.separator + "CSVsFile" + File.separator);

    private final String path;

    Directory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
