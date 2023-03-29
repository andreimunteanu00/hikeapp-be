package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.chatroom.ChatRoomDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPublicDTO;

import java.util.List;

public interface ChatRoomService {

    ChatRoomPrivateDTO createOrGetPrivateChatRoom(List<String> googleIds);
    List<ChatRoomDTO> getCurrentUserChatRooms();
    ChatRoomPublicDTO createOrGetPublicChatRoom(ChatRoomPublicDTO chatRoomPublicDTO) throws Exception;

}
