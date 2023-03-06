package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.mapper.RatingMapper;
import com.mnt.hikeapp.dto.rating.RatingForHikeDetailDTO;
import com.mnt.hikeapp.entity.Hike;
import com.mnt.hikeapp.entity.Rating;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.HikeRepository;
import com.mnt.hikeapp.repository.RatingRepository;
import com.mnt.hikeapp.service.RatingService;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private HikeRepository hikeRepository;
    private RatingRepository ratingRepository;
    private RatingMapper ratingMapper;
    @Override
    public Page<RatingForHikeDetailDTO> getRatingByHikeTitle(String hikeTitle, Pageable page) {
        return ratingMapper.ratingForHikeDetailDTOPage(ratingRepository.findByHikeTitle(hikeTitle, page));
    }

    @Override
    public RatingForHikeDetailDTO getRatingByCurrentUser(String hikeTitle) throws UserNotFoundException, HikeNotFoundException {
        Hike hike = hikeRepository.findByTitle(hikeTitle).orElse(null);
        if (hike == null) throw new HikeNotFoundException("Hike with title %s not found!", hikeTitle);
        User user = Util.getCurrentUser();
        if (user == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        Rating rating = ratingRepository.findByUserIdAndHikeId(user.getId(), hike.getId()).orElse(null);
        if (rating == null) {
            return null;
        }
        return ratingMapper.toRatingForHikeDetailDTO(rating);
    }
}
