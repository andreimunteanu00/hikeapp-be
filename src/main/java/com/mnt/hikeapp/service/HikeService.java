package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.hike.HikeDetailScreenDTO;
import com.mnt.hikeapp.dto.hike.HikeShowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HikeService {

    Page<HikeShowDTO> getHikeShowList(String title, String sort, Pageable page);
    HikeDetailScreenDTO getHikeDetailScreenByTitle(String hikeTitle);

}
