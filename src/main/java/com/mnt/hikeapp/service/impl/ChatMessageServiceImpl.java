package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.chatmessage.ChatMessageSenderDTO;
import com.mnt.hikeapp.dto.mapper.ChatMessageMapper;
import com.mnt.hikeapp.entity.ChatMessage;
import com.mnt.hikeapp.entity.ChatRoom;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.ChatMessageRepository;
import com.mnt.hikeapp.repository.ChatRoomRepository;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.ChatMessageService;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.exception.ChatRoomNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    @Transactional
    public void save(ChatMessageSenderDTO chatMessage) throws UserNotFoundException, ChatRoomNotFoundException {
        User sender = userRepository.findByUsername(chatMessage.getSender()).orElse(null);
        if (sender == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessage.getChatRoomId()).orElse(null);
        if (chatRoom == null) throw new ChatRoomNotFoundException(Messages.CHAT_ROOM_NOT_FOUND);
        ChatMessage newChatMessage = new ChatMessage();
        newChatMessage.setSender(sender);
        newChatMessage.setChatRoom(chatRoom);
        newChatMessage.setTimestamp(LocalDateTime.now());
        newChatMessage.setContent(chatMessage.getContent());
        chatMessageRepository.save(newChatMessage);
    }

    @Override
    @Transactional
    public Page<ChatMessageSenderDTO> getAllMessagesForPrivateChat(Long roomId, PageRequest page) {
        return chatMessageMapper.toChatMessageSenderListPageDTO(chatMessageRepository.findByChatRoomIdOrderByTimestampDesc(roomId, page));
    }
}
