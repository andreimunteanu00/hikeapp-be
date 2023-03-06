package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
