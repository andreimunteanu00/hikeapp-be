package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndHikeId(Long user_id, Long hike_id);

}
