package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.chatroom.ChatRoomDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPrivateDTO;
import com.mnt.hikeapp.dto.chatroom.ChatRoomPublicDTO;
import com.mnt.hikeapp.dto.mapper.ChatRoomMapper;
import com.mnt.hikeapp.entity.ChatRoom;
import com.mnt.hikeapp.entity.Picture;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.ChatRoomRepository;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.ChatRoomService;
import com.mnt.hikeapp.util.Constants;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.enums.ChatType;
import com.mnt.hikeapp.util.exception.ChatRoomNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    @Transactional
    public ChatRoomPrivateDTO createOrGetPrivateChatRoom(List<String> googleIds) {
        ChatRoom chatRoom = chatRoomRepository
                .findBySenderAndReceiverGoogleId(googleIds.get(0), googleIds.get(1)).orElse(null);
        ChatRoomPrivateDTO chatRoomPrivateDTO = null;
        if (chatRoom != null) {
            chatRoomPrivateDTO = chatRoomMapper.toChatRoomPrivateDTO(chatRoom);
        }
        return Objects.requireNonNullElseGet(chatRoomPrivateDTO, () -> {
            try {
                return chatRoomMapper.toChatRoomPrivateDTO(createChatRoom(googleIds, null, null));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private ChatRoom createChatRoom(List<String> googleIds, String groupName, Picture groupPicture) throws Exception {
        List<User> users = new ArrayList<>();
        googleIds.forEach(id -> users.add(userRepository.findByGoogleId(id).orElse(null)));
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUserList(users);
        if (googleIds.size() > 2) {
            chatRoom.setChatType(ChatType.PUBLIC);
            chatRoom.setName(groupName);
            if (groupPicture != null && groupPicture.getBase64() != null) {
                chatRoom.setPublicChatPhoto(groupPicture);
            } else {
                chatRoom.setPublicChatPhoto(new Picture(Util.fileToBase64(Path.of(Constants.PATH_DEFAULT_AVATAR))));
            }
        } else {
            chatRoom.setChatType(ChatType.PRIVATE);
        }
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    @Transactional
    public List<ChatRoomDTO> getCurrentUserChatRooms() {
        String googleId = Util.getCurrentUserGoogleId();
        return chatRoomMapper.toChatRoomListDTO(chatRoomRepository.findAllByUserGoogleId(googleId));
    }

    @Override
    @Transactional
    public ChatRoomPublicDTO createOrGetPublicChatRoom(ChatRoomPublicDTO chatRoomPublicDTO) throws Exception {
        ChatRoom chatRoom = createChatRoom(chatRoomPublicDTO.getGoogleIds(), chatRoomPublicDTO.getName(), chatRoomPublicDTO.getPublicChatPhoto());
        return chatRoomMapper.toChatRoomPublicDTO(chatRoom);
    }

    @Override
    public void leaveChat(Long chatRoomId) throws UserNotFoundException, ChatRoomNotFoundException {
        User user = userRepository.findByGoogleId(Util.getCurrentUserGoogleId()).orElse(null);
        if (user == null) throw new UserNotFoundException(Messages.USERNAME_NOT_FOUND);
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElse(null);
        if (chatRoom == null) throw new ChatRoomNotFoundException(Messages.CHAT_ROOM_NOT_FOUND);
        chatRoom.getUserList().removeIf(userInList -> userInList.getGoogleId().equals(user.getGoogleId()));
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public ChatRoomPublicDTO editPublicChatRoom(ChatRoomPublicDTO chatRoomPublicDTO) throws ChatRoomNotFoundException {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomPublicDTO.getId()).orElse(null);
        if (chatRoom == null) throw new ChatRoomNotFoundException(Messages.CHAT_ROOM_NOT_FOUND);
        chatRoom.setName(chatRoomPublicDTO.getName());
        if (chatRoomPublicDTO.getPublicChatPhoto() != null && chatRoomPublicDTO.getPublicChatPhoto().getBase64() != null
        && !Objects.equals(chatRoomPublicDTO.getPublicChatPhoto().getBase64(), chatRoom.getPublicChatPhoto().getBase64())) {
            chatRoom.setPublicChatPhoto(chatRoomPublicDTO.getPublicChatPhoto());
        }
        chatRoomRepository.save(chatRoom);
        return chatRoomPublicDTO;
    }
}
