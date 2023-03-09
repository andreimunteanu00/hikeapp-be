package com.mnt.hikeapp.controller;

import com.mnt.hikeapp.dto.hike.HikeDetailScreenDTO;
import com.mnt.hikeapp.dto.hike.HikeShowDTO;
import com.mnt.hikeapp.service.HikeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hike")
@AllArgsConstructor
public class HikeController {

    private final HikeService hikeService;

    @GetMapping("")
    private ResponseEntity<Page<HikeShowDTO>> getHikeShowList(@RequestParam(name = "title", defaultValue = "") String title,
                                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "10") int size,
                                                              @RequestParam(name = "sort", defaultValue = "desc") String typeSort,
                                                              @RequestParam(name = "sortField", defaultValue = "title") String sortField) {
        return ResponseEntity.ok(hikeService.getHikeShowList(title, sortField,
                PageRequest.of(page, size, typeSort.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending())));
    }

    @GetMapping("{hikeTitle}")
    private ResponseEntity<HikeDetailScreenDTO> getHikeDetailScreen(@PathVariable String hikeTitle) {
        return ResponseEntity.ok(hikeService.getHikeDetailScreenByTitle(hikeTitle));
    }
}
