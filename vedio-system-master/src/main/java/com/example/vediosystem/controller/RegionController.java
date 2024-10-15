package com.example.vediosystem.controller;

import com.example.vediosystem.domain.Region;
import com.example.vediosystem.domain.VSort;
import com.example.vediosystem.service.RegionService;
import com.example.vediosystem.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    /**
     * 获得全部分类
     * @return 全部分类
     */
    @GetMapping("/all")
    public Result getAllRegions(){
        List<Region> allSort = regionService.getAllRegion();
        return new Result(Code.GET_OK,allSort,"查询成功");
    }
}
