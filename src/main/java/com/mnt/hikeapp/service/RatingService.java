package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.rating.RatingByUserDTO;
import com.mnt.hikeapp.dto.rating.RatingForHikeDetailDTO;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.RatingNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    void rateHike(String hikeTitle, RatingByUserDTO rartingByUser) throws HikeNotFoundException, UserNotFoundException;
    void unrateHike(String hikeTitle) throws UserNotFoundException, HikeNotFoundException, RatingNotFoundException;
    Page<RatingForHikeDetailDTO> getRatingByHikeTitle(String hikeTitle, Pageable page);
    RatingForHikeDetailDTO getRatingByCurrentUser(String hikeTitle) throws UserNotFoundException, HikeNotFoundException;
}
