package com.mnt.hikeapp.dto.hike;

import com.mnt.hikeapp.entity.Picture;
import com.mnt.hikeapp.util.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HikeDetailScreenDTO {

    private String title;
    private String description;
    private Double allRatings;
    private Long numberRatings;
    private List<Picture> pictureList;
    private Difficulty difficulty;



}
