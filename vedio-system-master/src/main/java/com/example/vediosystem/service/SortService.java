package com.example.vediosystem.service;

import com.example.vediosystem.domain.VSort;

import java.util.List;

public interface SortService {
    /**
     * 获得所有分类
     * @return 所有分类
     */
    public List<VSort> getAllSort();
}
