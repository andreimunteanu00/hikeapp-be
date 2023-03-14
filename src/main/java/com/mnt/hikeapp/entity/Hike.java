package com.mnt.hikeapp.entity;

import com.mnt.hikeapp.util.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HIKE")
public class Hike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ALL_RATINGS")
    private Double allRatings;

    @Column(name ="NUMBER_RATINGS")
    private Long numberRatings;

    @Column(name = "DISTANCE")
    private Double distance;

    @OneToOne
    private Picture mainPicture;

    @OneToMany
    private List<Picture> pictureList;

    @Column(name = "DIFFICULTY")
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne
    private LatLng startPoint;

    @OneToOne
    private LatLng endPoint;

}
