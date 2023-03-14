package com.mnt.hikeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HIKE_HISTORY")
public class HikeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "HIKE_ID")
    private Hike hike;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ELAPSED_TIME")
    private Duration elapsedTime;

    @Column(name = "HIKE_POINTS")
    private Double hikePoints;

    @CreationTimestamp
    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

}
