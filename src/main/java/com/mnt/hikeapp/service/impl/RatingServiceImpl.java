package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.RatingForHikeDetailDTO;
import com.mnt.hikeapp.dto.mapper.RatingMapper;
import com.mnt.hikeapp.repository.RatingRepository;
import com.mnt.hikeapp.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private RatingMapper ratingMapper;
    @Override
    public Page<RatingForHikeDetailDTO> getRatingByHikeTitle(String hikeTitle, Pageable page) {
        return ratingMapper.ratingForHikeDetailDTOPage(ratingRepository.findByHikeTitle(hikeTitle, page));
    }
}
