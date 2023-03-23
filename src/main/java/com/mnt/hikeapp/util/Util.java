package com.mnt.hikeapp.util;

import com.mnt.hikeapp.dto.user.UserSecurityDTO;
import com.mnt.hikeapp.util.enums.ChatType;
import com.mnt.hikeapp.util.enums.Difficulty;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Util {

    public static String fileToBase64(Path path) throws Exception {
        byte[] fileBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    public static String getCurrentUserGoogleId() {
        UserSecurityDTO user = (UserSecurityDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getGoogleId();
    }

    public static double calculateHikePoints(Difficulty difficulty, double distance, double elapsedTime, double temperature) {
        return (difficulty.getValue() * 10) + (distance * 0.8) + (elapsedTime / 60 * 0.1) + ((21 - temperature) * 0.3);
    }

    public static ChatType getChatType(String chatType) {
        ChatType chatTypeEnum = null;
        for(ChatType type : ChatType.values()) {
            if(type.toString().equalsIgnoreCase(chatType)) {
                chatTypeEnum = type;
                break;
            }
        }
        return chatTypeEnum;
    }
}
