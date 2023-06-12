package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.HikeHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HikeHistoryRepository extends JpaRepository<HikeHistory, Long> {

    HikeHistory findFirstByHikeTitleAndUserIdOrderByCreatedDateTimeDesc(String hikeTitle, Long userId);
    @Query("SELECT hh FROM HikeHistory hh WHERE hh.hike.title LIKE :hikeTitle AND hh.user.id = :userId ORDER BY hh.createdDateTime ASC")
    Page<HikeHistory> findAllByHikeTitleAndUserIdOrderByCreatedDateTimeDesc(String hikeTitle, Long userId, Pageable pageable);
}
