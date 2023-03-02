package com.mnt.hikeapp.dto.rating;

import com.mnt.hikeapp.dto.user.UserShowDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RatingForHikeDetailDTO {

    private UserShowDTO user;
    private String comment;
    private LocalDateTime dateTimeRate;
    private Double rating;

}
