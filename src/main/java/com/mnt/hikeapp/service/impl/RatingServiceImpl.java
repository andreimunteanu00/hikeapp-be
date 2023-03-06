package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.mapper.RatingMapper;
import com.mnt.hikeapp.dto.rating.RatingByUserDTO;
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
import com.mnt.hikeapp.util.exception.RatingNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private HikeRepository hikeRepository;
    private RatingRepository ratingRepository;
    private RatingMapper ratingMapper;

    @Override
    @Transactional
    public void rateHike(String hikeTitle, RatingByUserDTO ratingByUser) throws HikeNotFoundException, UserNotFoundException {
        Hike hike = hikeRepository.findByTitle(hikeTitle).orElse(null);
        if (hike == null) throw new HikeNotFoundException("Hike with title %s not found!", hikeTitle);
        User user = Util.getCurrentUser();
        if (user == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        Rating rating = ratingRepository.findByUserIdAndHikeId(user.getId(), hike.getId()).orElse(null);
        ratingRepository.save(calculateRating(ratingByUser, hike, user, rating));
    }

    @Override
    public void unrateHike(String hikeTitle) throws UserNotFoundException, HikeNotFoundException, RatingNotFoundException {
        Hike hike = hikeRepository.findByTitle(hikeTitle).orElse(null);
        if (hike == null) throw new HikeNotFoundException("Hike with title %s not found!", hikeTitle);
        User user = Util.getCurrentUser();
        if (user == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        Rating rating = ratingRepository.findByUserIdAndHikeId(user.getId(), hike.getId()).orElse(null);
        if (rating == null) throw new RatingNotFoundException();
        if (hike.getNumberRatings() - 1 == 0) {
            hike.setAllRatings(0.0);
        } else {
            hike.setAllRatings((hike.getAllRatings() * hike.getNumberRatings() - rating.getRating()) / (hike.getNumberRatings() - 1));
        }
        hike.setNumberRatings(hike.getNumberRatings() - 1);
        ratingRepository.delete(rating);
    }

    @Override
    public Page<RatingForHikeDetailDTO> getRatingByHikeTitle(String hikeTitle, Pageable page) {
        return ratingMapper.ratingForHikeDetailDTOPage(ratingRepository.findByHikeTitle(hikeTitle, page));
    }

    @Override
    public RatingForHikeDetailDTO getRatingByCurrentUser(String hikeTitle) throws UserNotFoundException, HikeNotFoundException {
        Rating rating = getRatingByUserForHike(hikeTitle);
        if (rating == null) {
            return null;
        }
        return ratingMapper.toRatingForHikeDetailDTO(rating);
    }

    @Transactional
    protected Rating getRatingByUserForHike(String hikeTitle) throws HikeNotFoundException, UserNotFoundException {
        Hike hike = hikeRepository.findByTitle(hikeTitle).orElse(null);
        if (hike == null) throw new HikeNotFoundException("Hike with title %s not found!", hikeTitle);
        User user = Util.getCurrentUser();
        if (user == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        return ratingRepository.findByUserIdAndHikeId(user.getId(), hike.getId()).orElse(null);
    }

    private Rating calculateRating(RatingByUserDTO ratingByUser, Hike hike, User user, Rating rating) {
        Double oldRating = null;
        if (rating == null) {
            rating = new Rating();
        } else {
            oldRating = rating.getRating();
        }
        rating.setHike(hike);
        rating.setUser(user);
        rating.setRating(ratingByUser.getRating());
        rating.setComment(ratingByUser.getComment());
        if (hike.getNumberRatings() == null) {
            hike.setNumberRatings(1L);
        }
        if (hike.getAllRatings() == null) {
            hike.setAllRatings(rating.getRating());
        } else if (oldRating != null) {
            hike.setAllRatings((hike.getAllRatings() * hike.getNumberRatings() - oldRating + rating.getRating()) / hike.getNumberRatings());
        } else {
            hike.setNumberRatings(hike.getNumberRatings() + 1);
            hike.setAllRatings((hike.getAllRatings() * (hike.getNumberRatings() - 1) + rating.getRating()) / hike.getNumberRatings());
        }
        return rating;
    }
}
