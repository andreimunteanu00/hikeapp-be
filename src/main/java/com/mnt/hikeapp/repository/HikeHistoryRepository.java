package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.HikeHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HikeHistoryRepository extends JpaRepository<HikeHistory, Long> {

    HikeHistory findFirstByHikeTitleAndUserIdOrderByCreatedDateTimeDesc(String hikeTitle, Long userId);
}
