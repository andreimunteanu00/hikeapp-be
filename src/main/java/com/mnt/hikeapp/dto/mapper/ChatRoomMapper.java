package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
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

    public ChatRoomPrivateDTO toChatRoomDTO(ChatRoom chatRoom) {
        User user = null;
        for (User u : chatRoom.getUserList()) {
            if (!u.getGoogleId().equals(Util.getCurrentUserGoogleId())) {
                user = u;
            }
        }
        assert user != null;
        return new ChatRoomPrivateDTO(
            userMapper.toUserShowDTO(user)
        );
    }

    public List<ChatRoomPrivateDTO> toChatRoomListDTO(List<ChatRoom> chatRoomList) {
        return chatRoomList.stream().map(this::toChatRoomDTO).collect(Collectors.toList());
    }
}
