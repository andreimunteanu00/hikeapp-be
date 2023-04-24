package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.hikehistory.HikeHistoryFinishDTO;
import com.mnt.hikeapp.dto.user.UserShowDTO;
import com.mnt.hikeapp.entity.HikeHistory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HikeHistoryMapper {

    private final HikeMapper hikeMapper;

    public HikeHistoryFinishDTO toHikeShowDTO(HikeHistory hikeHistory) {
        return new HikeHistoryFinishDTO(
                hikeHistory.getCreatedDateTime(),
                hikeHistory.getElapsedTime(),
                hikeHistory.getHikePoints(),
                hikeMapper.toHikeShowDTO(hikeHistory.getHike())
        );
    }

    public Page<HikeHistoryFinishDTO> toHikeShowListPageDTO(Page<HikeHistory> hikeHistoryList) {
        List<HikeHistoryFinishDTO> aux = hikeHistoryList.stream().map(this::toHikeShowDTO).collect(Collectors.toList());
        return new PageImpl<>(aux);
    }
}
