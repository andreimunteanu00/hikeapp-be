package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.rating.RatingForHikeDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    Page<RatingForHikeDetailDTO> getRatingByHikeTitle(String hikeTitle, Pageable page);

}
