package com.example.vediosystem.service;

import com.example.vediosystem.domain.Region;
import com.example.vediosystem.domain.VSort;
import com.example.vediosystem.domain.Video;

import java.io.IOException;
import java.util.List;

public interface RegionService {
    /**
     * 获得所有分类
     * @return 所有分类
     */
    public List<Region> getAllRegion();
}
