package com.example.vediosystem.controller;

import com.example.vediosystem.domain.History;
import com.example.vediosystem.domain.V_History;
import com.example.vediosystem.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    /**
     * 获取用户历史记录接口
     * @param uId 用户ID
     * @param flag 标志位 0获取近一周 ， 1获取一个月 ， 2获取更早
     * @return 用户历史记录数组
     */
    @GetMapping("/{uId}/{flag}")
    public Result getUserHistories(@PathVariable int uId,@PathVariable int flag){
        List<V_History> userHistory = historyService.getUserHistory(uId, flag);
        return new Result(Code.GET_OK,userHistory,"获取成功");
    }

    /**
     * 获取历史记录
     * @param uId
     * @param vId
     * @return 保存结果提示
     */
    @GetMapping("/select/{uId}/{vId}")
    public Result selectHistory(@PathVariable int uId,@PathVariable int vId){
        int id = historyService.selectHistory(uId,vId);
        History history = historyService.selectHistoryById(id);
        return new Result(Code.SAVE_OK,history,"保存成功");
    }

    /**
     * 增加一条历史记录
     * @param
     * @return 保存结果提示
     */
    @PostMapping("/add")
    public Result addHistory(@RequestBody History history){
        System.out.println(history);
        historyService.addHistory(history);
        return new Result(Code.SAVE_OK,"保存成功");
    }

    /**
     * 更新历史记录
     * @param history 实现记忆播放
     * @return 保存结果提示
     */
    @PostMapping("/update")
    public Result updateHistory(@RequestBody History history){
        System.out.println(history);
        historyService.updateHistory(history);
        return new Result(Code.SAVE_OK,"保存成功");
    }
}
