package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.chatroom.ChatRoomDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPublicDTO;
import com.mnt.hikeapp.entity.ChatRoom;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChatRoomMapper {

    private final UserMapper userMapper;

    public ChatRoomPrivateDTO toChatRoomPrivateDTO(ChatRoom chatRoom) {
        User user = null;
        for (User u : chatRoom.getUserList()) {
            if (!u.getGoogleId().equals(Util.getCurrentUserGoogleId())) {
                user = u;
            }
        }
        assert user != null;
        return new ChatRoomPrivateDTO(
                chatRoom.getId(),
                userMapper.toUserShowDTO(user)
        );
    }

    public ChatRoomPublicDTO toChatRoomPublicDTO(ChatRoom chatRoom) {
        return new ChatRoomPublicDTO(
                chatRoom.getId(),
                chatRoom.getName(),
                chatRoom.getPublicChatPhoto(),
                userMapper.toUserShowListDTO(chatRoom.getUserList()),
                null);
    }

    public ChatRoomDTO toChatRoomDTO(ChatRoom chatRoom) {
        if (chatRoom.getUserList().size() > 2) {
            return new ChatRoomDTO(
                    chatRoom.getId(),
                    null,
                    chatRoom.getName(),
                    chatRoom.getPublicChatPhoto(),
                    userMapper.toUserShowListDTO(chatRoom.getUserList())
            );
        } else {
            User user = null;
            for (User u : chatRoom.getUserList()) {
                if (!u.getGoogleId().equals(Util.getCurrentUserGoogleId())) {
                    user = u;
                }
            }
            assert user != null;
            return new ChatRoomDTO(
                    chatRoom.getId(),
                    userMapper.toUserShowDTO(user),
                    null,
                    null,
                    null
            );
        }

    }

    public List<ChatRoomDTO> toChatRoomListDTO(List<ChatRoom> chatRoomList) {
        return chatRoomList.stream().map(this::toChatRoomDTO).collect(Collectors.toList());
    }
}
