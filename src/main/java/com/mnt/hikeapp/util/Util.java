package com.mnt.hikeapp.util;

import com.mnt.hikeapp.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Util {

    public static String fileToBase64(Path path) throws Exception {
        byte[] fileBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static boolean checkSameUser(String googleId) {
        return getCurrentUser().getGoogleId().equals(googleId);
    }
}
