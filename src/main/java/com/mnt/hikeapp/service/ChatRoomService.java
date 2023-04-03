package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.chatroom.ChatRoomDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPublicDTO;
import com.mnt.hikeapp.util.exception.ChatRoomNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;

import java.util.List;

public interface ChatRoomService {

    ChatRoomPrivateDTO createOrGetPrivateChatRoom(List<String> googleIds);
    List<ChatRoomDTO> getCurrentUserChatRooms();
    ChatRoomPublicDTO createOrGetPublicChatRoom(ChatRoomPublicDTO chatRoomPublicDTO) throws Exception;
    void leaveChat(Long chatRoomId) throws UserNotFoundException, ChatRoomNotFoundException;
    ChatRoomPublicDTO editPublicChatRoom(ChatRoomPublicDTO chatRoomPublicDTO) throws ChatRoomNotFoundException;

}
