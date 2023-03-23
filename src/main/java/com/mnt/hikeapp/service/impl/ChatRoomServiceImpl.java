package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
import com.mnt.hikeapp.dto.mapper.ChatRoomMapper;
import com.mnt.hikeapp.entity.ChatRoom;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.ChatRoomRepository;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.ChatRoomService;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.enums.ChatType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatRoomMapper chatRoomMapper;
    @Override
    @Transactional
    public void createChatRoom(List<String> googleIds) {
        List<User> users = new ArrayList<>();
        googleIds.forEach(id -> users.add(userRepository.findByGoogleId(id).orElse(null)));
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUserList(users);
        if (googleIds.size() > 2) {
            chatRoom.setChatType(ChatType.PUBLIC);
        } else {
            chatRoom.setChatType(ChatType.PRIVATE);
        }
        chatRoomRepository.save(chatRoom);
    }

    @Override
    @Transactional
    public List<ChatRoomPrivateDTO> getCurrentUserChatRooms() {
        String googleId = Util.getCurrentUserGoogleId();
        return chatRoomMapper.toChatRoomListDTO(chatRoomRepository.findAllByUserGoogleId(googleId));
    }
}
