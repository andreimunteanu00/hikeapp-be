package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.chatmessage.ChatMessageSenderDTO;
import com.mnt.hikeapp.entity.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ChatMessageMapper {

    ChatMessageSenderDTO toChatMessageSenderDTO(ChatMessage chatMessage) {
        return new ChatMessageSenderDTO(
                chatMessage.getSender().getUsername(),
                chatMessage.getContent(),
                chatMessage.getChatRoom().getId(),
                chatMessage.getTimestamp()
        );
    }

    public Page<ChatMessageSenderDTO> toChatMessageSenderListPageDTO(Page<ChatMessage> userList) {
        List<ChatMessageSenderDTO> aux = userList.stream().map(this::toChatMessageSenderDTO).collect(Collectors.toList());
        Collections.reverse(aux);
        return new PageImpl<>(aux);
    }
}
