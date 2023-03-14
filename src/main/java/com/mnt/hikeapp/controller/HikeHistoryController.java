package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.service.HikeHistoryService;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import com.mnt.hikeapp.util.model.HikeSummary;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hike-history")
@AllArgsConstructor
public class HikeHistoryController {

    private final HikeHistoryService hikeHistoryService;

    @PostMapping("")
    public ResponseEntity<Void> postHikeHistory(@RequestBody HikeSummary hikeSummary) throws UserNotFoundException, HikeNotFoundException {
        hikeHistoryService.postHistory(hikeSummary);
        return ResponseEntity.noContent().build();
    }
}
