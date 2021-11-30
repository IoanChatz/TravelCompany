package com.travelcompany.eshop.model;

import java.io.File;

public enum Directory {

    FILE_DIRECTORY(System.getProperty("user.home") + File.separator + "IdeaProjects" + File.separator
            + "TravelCompany" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator),

    BACKUP_DIRECTORY(System.getProperty("user.home") + File.separator + "CSVFiles" + File.separator),

    REPORTS_DIRECTORY(System.getProperty("user.home") + File.separator + "Reports" + File.separator);

    private final String path;

    Directory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
