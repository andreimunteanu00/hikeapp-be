package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT c from ChatRoom c JOIN c.userList u where u.googleId =:googleId")
    List<ChatRoom> findAllByUserGoogleId(@Param("googleId") String googleId);

    @Query("SELECT c FROM ChatRoom c " +
            "JOIN c.userList u1 " +
            "JOIN c.userList u2 " +
            "WHERE c.chatType = 'PRIVATE' AND u1.googleId = :senderGoogleId AND u2.googleId = :receiverGoogleId")
    Optional<ChatRoom> findBySenderAndReceiverGoogleId(@Param("senderGoogleId") String senderGoogleId, @Param("receiverGoogleId") String receiverGoogleId);
}
