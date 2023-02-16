package com.mnt.hikeapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Base64;

public class Util {

    public static String fileToBase64(Path path) throws Exception {
        byte[] fileBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(fileBytes);
    }

}
