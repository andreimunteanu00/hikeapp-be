package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.hikehistory.HikeHistoryFinishDTO;
import com.mnt.hikeapp.util.exception.HikeHistoryNotFoundException;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import com.mnt.hikeapp.util.model.HikeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface HikeHistoryService {

    void postHistory(HikeSummary hikeSummary) throws HikeNotFoundException, UserNotFoundException;
    HikeHistoryFinishDTO getFinishHistoryByTitle(String hikeTitle) throws UserNotFoundException, HikeHistoryNotFoundException;
    Page<HikeHistoryFinishDTO> getAllHikeHistoryForCurrentUser(String title, PageRequest page) throws UserNotFoundException, HikeHistoryNotFoundException;
}
