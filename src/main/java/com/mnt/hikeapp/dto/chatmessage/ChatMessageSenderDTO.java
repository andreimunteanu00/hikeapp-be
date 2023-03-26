package com.mnt.hikeapp.dto.chatmessage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatMessageSenderDTO {

    private String sender;
    private String content;
    private Long chatRoomId;
    private LocalDateTime timestamp;

}
