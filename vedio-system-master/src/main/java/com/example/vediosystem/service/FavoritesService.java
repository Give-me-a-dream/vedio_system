package com.example.vediosystem.service;

import com.example.vediosystem.domain.U_Video;

import java.util.List;

public interface FavoritesService {
    /**
     * 收藏视频
     * @param vId 视频ID
     */
    public void addVideo(int uId,int vId);

    /**
     * 获得用户收藏夹里的所有视频
     * @param uId 用户Id
     * @return 视频列表
     */
    public List<U_Video> getUserFavorites(int uId);
}
