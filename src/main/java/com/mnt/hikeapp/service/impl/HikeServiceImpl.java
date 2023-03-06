package com.mnt.hikeapp.service.impl;

import com.mnt.hikeapp.dto.hike.HikeDetailScreenDTO;
import com.mnt.hikeapp.dto.hike.HikeShowDTO;
import com.mnt.hikeapp.dto.mapper.HikeMapper;
import com.mnt.hikeapp.repository.HikeRepository;
import com.mnt.hikeapp.service.HikeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@AllArgsConstructor
public class HikeServiceImpl implements HikeService {

    private final HikeRepository hikeRepository;
    private final HikeMapper hikeMapper;

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

    @Override
    public HikeDetailScreenDTO getHikeDetailScreenByTitle(String hikeTitle) {
        return hikeMapper.toHikeDetailScreenDTO(Objects.requireNonNull(hikeRepository.findByTitle(hikeTitle).orElse(null)));
    }
}
