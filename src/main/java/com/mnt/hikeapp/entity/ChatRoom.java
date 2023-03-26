package com.mnt.hikeapp.entity;

import com.mnt.hikeapp.util.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CHAT_ROOM")
public class ChatRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Picture publicChatPhoto;

    @OneToMany
    private List<ChatMessage> chatMessages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CHAT_ROOM_USER",
            joinColumns = @JoinColumn(name = "CHAT_ROOM_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> userList;

    @CreationTimestamp
    @Column(name = "CREATE_DATE_TIME")
    private LocalDateTime createDateTime;

    @Column(name = "CHAT_TYPE")
    @Enumerated(EnumType.STRING)
    private ChatType chatType;
}
