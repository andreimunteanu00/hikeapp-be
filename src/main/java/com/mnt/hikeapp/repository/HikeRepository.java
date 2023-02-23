package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.Hike;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface HikeRepository extends JpaRepository<Hike, Long> {

    Page<Hike> findByTitleContainingIgnoreCaseOrderByTitle(String title, Pageable pageable);
    Page<Hike> findAllByOrderByAllRatings(Pageable pageable);
    Page<Hike> findAllByOrderByNumberRatings(Pageable pageable);
    Optional<Hike> findByTitle(String title);

}
