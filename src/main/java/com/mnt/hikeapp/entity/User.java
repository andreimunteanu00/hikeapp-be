package com.mnt.hikeapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_LOGIN")
    private boolean isFirstLogin;

    @Column(name = "BLOCKED")
    private boolean isBlocked;

    @Column(name = "GOOGLE_ID", nullable = false, unique = true)
    private String googleId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Lob
    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;

    @CreationTimestamp
    @Column(name = "CREATE_DATE_TIME")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE_TIME")
    private LocalDateTime updateDateTime;

}
