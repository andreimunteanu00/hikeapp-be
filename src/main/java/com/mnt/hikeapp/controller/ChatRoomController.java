package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.chatroom.ChatRoomDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPublicDTO;
import com.mnt.hikeapp.service.ChatRoomService;
import com.mnt.hikeapp.util.exception.ChatRoomNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat-room")
@AllArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/current-user")
    public ResponseEntity<List<ChatRoomDTO>> getCurrentUserChatRooms() {
        return ResponseEntity.ok(chatRoomService.getCurrentUserChatRooms());
    }

    @PostMapping("/create-or-get/private")
    public ResponseEntity<ChatRoomPrivateDTO> createOrGetPrivateChatRoom(@RequestBody List<String> googleIds) {
        return ResponseEntity.ok(chatRoomService.createOrGetPrivateChatRoom(googleIds));
    }

    @PostMapping("/create-or-get/public")
    public ResponseEntity<ChatRoomPublicDTO> createOrGetPublicChatRoom(@RequestBody ChatRoomPublicDTO chatRoomPublicDTO) throws Exception {
        return ResponseEntity.ok(chatRoomService.createOrGetPublicChatRoom(chatRoomPublicDTO));
    }

    @PutMapping("/edit")
    public ResponseEntity<ChatRoomPublicDTO> editPublicChatRoom(@RequestBody ChatRoomPublicDTO chatRoomPublicDTO) throws Exception {
        return ResponseEntity.ok(chatRoomService.editPublicChatRoom(chatRoomPublicDTO));
    }

    @DeleteMapping("/leave/current-user/{chatRoomId}")
    public ResponseEntity<Void> leaveChat(@PathVariable Long chatRoomId) throws UserNotFoundException, ChatRoomNotFoundException {
        chatRoomService.leaveChat(chatRoomId);
        return ResponseEntity.noContent().build();
    }
}
