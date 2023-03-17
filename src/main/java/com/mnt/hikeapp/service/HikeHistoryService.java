package com.mnt.hikeapp.service;

import com.mnt.hikeapp.dto.hikehistory.HikeHistoryFinishDTO;
import com.mnt.hikeapp.util.exception.HikeHistoryNotFoundException;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import com.mnt.hikeapp.util.model.HikeSummary;

public interface HikeHistoryService {
    void postHistory(HikeSummary hikeSummary) throws HikeNotFoundException, UserNotFoundException;
    HikeHistoryFinishDTO getFinishHistoryByTitle(String hikeTitle) throws UserNotFoundException, HikeHistoryNotFoundException;
}
