package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
import com.mnt.hikeapp.service.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat-room")
@AllArgsConstructor
public class ChatRoomController {

    private ChatRoomService chatRoomService;

    @GetMapping("/current-user")
    public ResponseEntity<List<ChatRoomPrivateDTO>> getCurrentUserChatRooms() {
        return ResponseEntity.ok(chatRoomService.getCurrentUserChatRooms());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createChatRoom(@RequestBody List<String> googleIds) {
        chatRoomService.createChatRoom(googleIds);
        return ResponseEntity.noContent().build();
    }
}
