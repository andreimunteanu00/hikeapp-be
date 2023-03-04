package com.mnt.hikeapp.dto.hike;

import com.mnt.hikeapp.entity.Picture;
import com.mnt.hikeapp.util.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HikeShowDTO {

    private String title;
    private Double allRatings;
    private Long numberRatings;
    private Picture mainPicture;
    private Difficulty difficulty;

}
