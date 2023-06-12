package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndHikeId(Long userId, Long hikeId);
    Page<Rating> findByHikeTitle(String hiketitle, Pageable pageable);

}
