package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT c from ChatRoom c JOIN c.userList u where u.googleId =:googleId")
    List<ChatRoom> findAllByUserGoogleId(@Param("googleId") String googleId);
}
