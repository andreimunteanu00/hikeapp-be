package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.hikehistory.HikeHistoryFinishDTO;
import com.mnt.hikeapp.dto.mapper.HikeHistoryMapper;
import com.mnt.hikeapp.entity.Hike;
import com.mnt.hikeapp.entity.HikeHistory;
import com.mnt.hikeapp.entity.User;
import com.mnt.hikeapp.repository.HikeHistoryRepository;
import com.mnt.hikeapp.repository.HikeRepository;
import com.mnt.hikeapp.repository.UserRepository;
import com.mnt.hikeapp.service.HikeHistoryService;
import com.mnt.hikeapp.util.Messages;
import com.mnt.hikeapp.util.Util;
import com.mnt.hikeapp.util.exception.HikeHistoryNotFoundException;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import com.mnt.hikeapp.util.model.HikeSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class HikeHistoryServiceImpl implements HikeHistoryService {

    private final HikeHistoryRepository hikeHistoryRepository;
    private final HikeRepository hikeRepository;
    private final UserRepository userRepository;
    private final HikeHistoryMapper hikeHistoryMapper;
    @Override
    @Transactional
    public void postHistory(HikeSummary hikeSummary) throws HikeNotFoundException, UserNotFoundException {
        HikeHistory hikeHistory = new HikeHistory();
        Hike hike = hikeRepository.findByTitle(hikeSummary.getHikeTitle()).orElse(null);
        if (hike == null) throw new HikeNotFoundException("Hike with title %s not found!", hikeSummary.getHikeTitle());
        User user = userRepository.findByGoogleId(Util.getCurrentUserGoogleId()).orElse(null);
        if (user == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        double score = Util.calculateHikePoints(hike.getDifficulty(), hike.getDistance(), hikeSummary.getElapsedTime().getSeconds(), hikeSummary.getTemperatureAverage());
        hikeHistory.setHike(hike);
        user.setHikePoints(user.getHikePoints() + score);
        hikeHistory.setUser(user);
        hikeHistory.setHikePoints(score);
        hikeHistory.setElapsedTime(hikeSummary.getElapsedTime());
        hikeHistoryRepository.save(hikeHistory);
    }

    @Override
    public HikeHistoryFinishDTO getFinishHistoryByTitle(String hikeTitle) throws UserNotFoundException, HikeHistoryNotFoundException {
        User user = userRepository.findByGoogleId(Util.getCurrentUserGoogleId()).orElse(null);
        if (user == null) throw new UserNotFoundException(Messages.USER_NOT_FOUND);
        HikeHistory hikeHistory = hikeHistoryRepository
                .findFirstByHikeTitleAndUserIdOrderByCreatedDateTimeDesc(hikeTitle, user.getId());
        if (hikeHistory == null) throw new HikeHistoryNotFoundException("hike history not found!");
        return hikeHistoryMapper.toHikeShowDTO(hikeHistory);
    }
}
