package com.mnt.hikeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HikeShowDTO {

    private String title;
    private String description;
    private Double allRatings;
    private Long numberRatings;

}
