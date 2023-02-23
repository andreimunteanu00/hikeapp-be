package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.HikeShowDTO;
import com.mnt.hikeapp.dto.RatingByUserDTO;
import com.mnt.hikeapp.dto.mapper.HikeMapper;
import com.mnt.hikeapp.entity.Hike;
import com.mnt.hikeapp.entity.Rating;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.HikeRepository;
import com.mnt.hikeapp.repository.RatingRepository;
import com.mnt.hikeapp.service.HikeService;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class HikeServiceImpl implements HikeService {

    private final HikeRepository hikeRepository;
    private final RatingRepository ratingRepository;
    private final HikeMapper hikeMapper;

    @Override
    @Transactional
    public void rateHike(String hikeTitle, RatingByUserDTO ratingByUser) throws HikeNotFoundException, UserNotFoundException {
        Hike hike = hikeRepository.findByTitle(hikeTitle).orElse(null);
        if (hike == null) throw new HikeNotFoundException("Hike with title %s not found!", hikeTitle);
        User user = Util.getCurrentUser();
        if (user == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        Rating rating = ratingRepository.findByUserIdAndHikeId(user.getId(), hike.getId()).orElse(null);
        rating = calculateRating(ratingByUser, hike, user, rating);
        ratingRepository.save(rating);
    }

    @Override
    @Transactional
    public Page<HikeShowDTO> getHikeShowList(String title, String sort, Pageable page) {
        Page<HikeShowDTO> hikeShowListDTOPage;
        if (!title.isEmpty()) {
            hikeShowListDTOPage = hikeMapper.toHikeShowListPageDTO(hikeRepository.findByTitleContainingIgnoreCaseOrderByTitle(title, page));
        } else {
            switch (sort) {
                case "allRatings":
                    hikeShowListDTOPage = hikeMapper.toHikeShowListPageDTO(hikeRepository.findAllByOrderByAllRatings(page));
                    break;
                case "numberRatings":
                    hikeShowListDTOPage = hikeMapper.toHikeShowListPageDTO(hikeRepository.findAllByOrderByNumberRatings(page));
                    break;
                default:
                    hikeShowListDTOPage = hikeMapper.toHikeShowListPageDTO(hikeRepository.findByTitleContainingIgnoreCaseOrderByTitle("", page));
                    break;
            }
        }
        return hikeShowListDTOPage;
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
