package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.HikeShowDTO;
import com.mnt.hikeapp.entity.Hike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HikeMapper {

    public HikeShowDTO toHikeShowDTO(Hike hike) {
        return new HikeShowDTO(
                hike.getTitle(),
                hike.getDescription(),
                hike.getAllRatings(),
                hike.getNumberRatings()
        );
    }

    public Page<HikeShowDTO> toHikeShowListPageDTO(Page<Hike> hikeList) {
        List<HikeShowDTO> aux = hikeList.stream().map(this::toHikeShowDTO).collect(Collectors.toList());
        return new PageImpl<>(aux);
    }
}
