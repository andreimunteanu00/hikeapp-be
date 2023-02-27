package com.mnt.hikeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RatingForHikeDetailDTO {

    private HikeShowDTO hike;
    private UserShowDTO user;
    private String comment;
    private LocalDateTime dateTimeRate;
    private Double rating;

}
