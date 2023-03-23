package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;

import java.util.List;

public interface ChatRoomService {
    void createChatRoom(List<String> googleIds);

    List<ChatRoomPrivateDTO> getCurrentUserChatRooms();
}
