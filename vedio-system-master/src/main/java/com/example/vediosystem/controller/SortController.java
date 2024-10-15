package com.example.vediosystem.controller;

import com.example.vediosystem.domain.VSort;
import com.example.vediosystem.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sort")
public class SortController {

    @Autowired
    SortService service;

    /**
     * 获得全部分类
     * @return 全部分类
     */
    @GetMapping("/all")
    public Result getAllSorts(){
        List<VSort> allSort = service.getAllSort();
        return new Result(Code.GET_OK,allSort,"查询成功");
    }
}
