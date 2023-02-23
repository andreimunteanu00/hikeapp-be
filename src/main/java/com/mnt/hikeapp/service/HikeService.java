package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.HikeShowDTO;
import com.mnt.hikeapp.dto.RatingByUserDTO;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HikeService {

    void rateHike(String hikeTitle, RatingByUserDTO rartingByUser) throws HikeNotFoundException, UserNotFoundException;
    Page<HikeShowDTO> getHikeShowList(String title, String sort, Pageable page);
}
