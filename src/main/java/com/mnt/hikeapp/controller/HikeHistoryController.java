package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.hikehistory.HikeHistoryFinishDTO;
import com.mnt.hikeapp.service.HikeHistoryService;
import com.mnt.hikeapp.util.exception.HikeHistoryNotFoundException;
import com.mnt.hikeapp.util.exception.HikeNotFoundException;
import com.mnt.hikeapp.util.exception.UserNotFoundException;
import com.mnt.hikeapp.util.model.HikeSummary;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("finish-hike/{hikeTitle}")
    public ResponseEntity<HikeHistoryFinishDTO> getHikeHistoryFinish(@PathVariable String hikeTitle) throws UserNotFoundException, HikeHistoryNotFoundException {
        return ResponseEntity.ok(hikeHistoryService.getFinishHistoryByTitle(hikeTitle));
    }

    @GetMapping("")
    public ResponseEntity<Page<HikeHistoryFinishDTO>> getAllHikeHistoryForCurrentUser(@RequestParam(name = "title", defaultValue = "") String title,
                                                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                      @RequestParam(name = "size", defaultValue = "10") int size) throws UserNotFoundException, HikeHistoryNotFoundException {
        return ResponseEntity.ok(hikeHistoryService.getAllHikeHistoryForCurrentUser(title, PageRequest.of(page, size)));
    }
}
