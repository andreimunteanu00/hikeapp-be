package com.mnt.hikeapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "GOOGLE_ID", nullable = false)
    private String googleId;

    @Column(name = "USERNAME")
    private String username;

}
