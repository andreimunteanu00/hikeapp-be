package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.rating.RatingForHikeDetailDTO;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    Page<RatingForHikeDetailDTO> getRatingByHikeTitle(String hikeTitle, Pageable page);
    RatingForHikeDetailDTO getRatingByCurrentUser(String hikeTitle) throws UserNotFoundException, HikeNotFoundException;
}
