package com.mnt.hikeapp.dto.mapper;

import com.mnt.hikeapp.dto.latlng.LatLngDTO;
import com.mnt.hikeapp.entity.LatLng;
import org.springframework.stereotype.Component;

@Component
public class LatLngMapper {

    public LatLngDTO toLatLngDTO(LatLng point) {
        return new LatLngDTO(
                point.getLatitude(),
                point.getLongitude()
        );
    }

}
