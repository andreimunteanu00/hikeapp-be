package com.mnt.hikeapp.dto.hikehistory;

import com.mnt.hikeapp.dto.hike.HikeShowDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HikeHistoryFinishDTO {

    private LocalDateTime createdDateTime;
    private Duration elapsedTime;
    private Double hikePoints;
    private HikeShowDTO hike;

}
