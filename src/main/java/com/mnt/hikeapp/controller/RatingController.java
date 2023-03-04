package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.rating.RatingForHikeDetailDTO;
import com.mnt.hikeapp.service.RatingService;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rating")
@AllArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("byHikeTitle")
    private ResponseEntity<Page<RatingForHikeDetailDTO>> getByHikeByTitle(@RequestParam(name = "hikeTitle") String hikeTitle,
                                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(ratingService.getRatingByHikeTitle(hikeTitle,
                PageRequest.of(page, size, Sort.by("dateTimeRate").descending())));
    }

    @GetMapping("byCurrentUser/{hikeTitle}")
    private ResponseEntity<RatingForHikeDetailDTO> getByCurrentUser(@PathVariable String hikeTitle) throws UserNotFoundException, HikeNotFoundException {
        return ResponseEntity.ok(ratingService.getRatingByCurrentUser(hikeTitle));
    }
}
