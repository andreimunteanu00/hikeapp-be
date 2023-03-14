package com.mnt.hikeapp.util.model;

import lombok.Data;

import java.time.Duration;

@Data
public class HikeSummary {

    private String hikeTitle;
    private Duration elapsedTime;
    private Double temperatureAverage;

}
