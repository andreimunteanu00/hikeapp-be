package com.mnt.hikeapp.dto.chatroom;

import com.mnt.hikeapp.dto.user.UserShowDTO;
import com.mnt.hikeapp.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatRoomPublicDTO {

    private Long id;
    private String name;
    private Picture publicChatPhoto;
    private List<UserShowDTO> userList;
    private List<String> googleIds;

}
