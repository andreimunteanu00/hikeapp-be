package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.hikehistory.HikeHistoryFinishDTO;
import com.mnt.hikeapp.entity.HikeHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HikeHistoryMapper {

    public HikeHistoryFinishDTO toHikeShowDTO(HikeHistory hikeHistory) {
        return new HikeHistoryFinishDTO(
                hikeHistory.getCreatedDateTime(),
                hikeHistory.getElapsedTime(),
                hikeHistory.getHike().getTitle(),
                hikeHistory.getHikePoints()
        );
    }
}
