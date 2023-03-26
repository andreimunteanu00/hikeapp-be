package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.chatmessage.ChatMessageSenderDTO;
import com.mnt.hikeapp.util.exception.ChatRoomNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ChatMessageService {

    void save(ChatMessageSenderDTO chatMessage) throws UserNotFoundException, ChangeSetPersister.NotFoundException, ChatRoomNotFoundException;
    Page<ChatMessageSenderDTO> getAllMessagesForPrivateChat(Long roomId, PageRequest page);

}
