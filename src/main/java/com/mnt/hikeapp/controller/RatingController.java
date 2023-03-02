package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.rating.RatingForHikeDetailDTO;
import com.mnt.hikeapp.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rating")
@AllArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("byHikeTitle")
    private ResponseEntity<Page<RatingForHikeDetailDTO>> getHikeByTitle(@RequestParam(name = "hikeTitle") String hikeTitle,
                                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(ratingService.getRatingByHikeTitle(hikeTitle,
                PageRequest.of(page, size, Sort.by("dateTimeRate").descending())));
    }
}
