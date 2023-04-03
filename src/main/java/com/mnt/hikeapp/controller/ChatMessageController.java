package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.chatmessage.ChatMessageSenderDTO;
import com.mnt.hikeapp.service.ChatMessageService;
import com.mnt.hikeapp.util.exception.ChatRoomNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void handleChatMessage(@RequestBody ChatMessageSenderDTO chatMessage) throws UserNotFoundException, ChatRoomNotFoundException, ChangeSetPersister.NotFoundException {
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSend("/topic/room/" + chatMessage.getChatRoomId(), chatMessage);
    }

    @GetMapping("/api/chat-message/all/{roomId}")
    public ResponseEntity<Page<ChatMessageSenderDTO>> getAllMessagesForPrivateChat(@PathVariable Long roomId,
                                                                                   @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(chatMessageService.getAllMessagesForPrivateChat(roomId, PageRequest.of(page, 20)));
    }
}
