package com.mnt.hikeapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Clob;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_LOGIN")
    private boolean isFirstLogin;

    @Column(name = "GOOGLE_ID", nullable = false, unique = true)
    private String googleId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Lob
    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;

    @CreationTimestamp
    @Column(name = "CREATE_DATE_TIME")
    private LocalDateTime createDateTime;

    @CreationTimestamp
    @Column(name = "UPDATE_DATE_TIME")
    private LocalDateTime updateDateTime;

}
