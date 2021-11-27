package com.travelcompany.eshop.utils;

import java.io.File;

public enum Directory {

    FILE_DIRECTORY(System.getProperty("user.home") + File.separator + "IdeaProjects" + File.separator
            + "TravelCompany" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator);

    private final String path;

    Directory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
