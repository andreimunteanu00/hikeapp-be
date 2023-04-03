package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Page<ChatMessage> findByChatRoomIdOrderByTimestampDesc(@Param("chatRoomId") Long chatRoomId, Pageable pageable);

}
