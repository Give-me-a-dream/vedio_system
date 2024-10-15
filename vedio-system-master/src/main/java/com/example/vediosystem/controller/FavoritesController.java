package com.example.vediosystem.controller;

import com.example.vediosystem.domain.U_Video;
import com.example.vediosystem.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {
    @Autowired
    FavoritesService favoritesService;

    /**
     * 往收藏夹里收藏视频
     * @param uId 用户Id
     * @param vId 视频Id
     * @return 保存结果
     */
    @GetMapping("/add/{uId}/{vId}")
    public Result addFavorites(@PathVariable int uId,@PathVariable int vId){
        favoritesService.addVideo(uId, vId);
        return new Result(Code.SAVE_OK,"保存成功");
    }

    @GetMapping("/{uId}")
    public Result getUserFavorites(@PathVariable int uId){
        List<U_Video> userFavorites = favoritesService.getUserFavorites(uId);
        return new Result(Code.GET_OK,userFavorites,"获取成功");
    }
}
