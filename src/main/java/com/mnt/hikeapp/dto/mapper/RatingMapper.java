package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.HikeShowDTO;
import com.mnt.hikeapp.dto.RatingForHikeDetailDTO;
import com.mnt.hikeapp.dto.UserShowDTO;
import com.mnt.hikeapp.entity.Rating;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RatingMapper {

    private UserMapper userMapper;
    private HikeMapper hikeMapper;

    public RatingForHikeDetailDTO toRatingForHikeDetailDTO(Rating rating) {
        HikeShowDTO hikeShowDTO = hikeMapper.toHikeShowDTO(rating.getHike());
        UserShowDTO userShowDTO = userMapper.toUserShowDTO(rating.getUser());
        return new RatingForHikeDetailDTO(
                hikeShowDTO,
                userShowDTO,
                rating.getComment(),
                rating.getDateTimeRate(),
                rating.getRating()
        );
    }

    public Page<RatingForHikeDetailDTO> ratingForHikeDetailDTOPage (Page<Rating> ratingList) {
        List<RatingForHikeDetailDTO> aux = ratingList.stream().map(this::toRatingForHikeDetailDTO).collect(Collectors.toList());
        return new PageImpl<>(aux);
    }
}
