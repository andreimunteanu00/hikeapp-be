package com.mnt.hikeapp.dto.hikehistory;

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
    private String hikeTitle;
    private Double hikePoints;

}