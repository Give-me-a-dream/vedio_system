package com.example.vediosystem.service;

import com.example.vediosystem.domain.U_Video;
import com.example.vediosystem.domain.Video;
import com.example.vediosystem.domain.VideoInfo;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    /**
     * 根据视频地址，对视频文件进行切片，转化为m3u8文件
     * @param path 文件地址，不含文件名
     * @param fileName 文件名，含后缀
     */
    public Video ffmpegVideo(String path,String fileName) throws IOException;

    /**
     * 通过视频名称搜索视频ID
     * @param name 视频名称
     * @return 视频实体类
     */
    public Video selectIdByName(String name);

    /**
     * 上传视频信息
     * @param video 视频实体类，含刚才上传到本地视频的信息
     */
    public void uploadVideoInfo(VideoInfo video);

    /**
     * 通过ID搜索视频
     * @param id 视频ID
     * @return 视频实体类
     */
    public U_Video selectById(int id);

    /**
     * 更新视频封面的地址
     * @param url 视频封面的存储地址
     */
    public void updateCover(int id,String url);

    /**
     * 根据视频标题查询视频
     * @param name 标题
     * @return 符合的结果项
     */
    public List<U_Video> selectVideos(String name);

    /**
     * 给视频点赞
     * @param id 视频ID
     */
    public void likeVideo(int id);

    /**
     * 给视频增加播放量
     * @param id 视频ID
     */
    public void addVideoPlays(int id);

    /**
     * 根据分类和属地分页查询视频，以播放量排序
     * @param sorts 分类ID数组
     * @param regions 属地ID数组
     * @param limit 限制每页数量
     * @param page 页码
     * @return 符合条件的视频数组
     */
    public List<U_Video> getMPVideos(List<Integer> sorts,List<Integer> regions,int limit,int page);

    /**
     * 根据分类和属地分页查询视频，以点赞排序
     * @param sorts 分类ID数组
     * @param regions 属地ID数组
     * @param limit 限制每页数量
     * @param page 页码
     * @return 符合条件的视频数组
     */
    public List<U_Video> getMLVideos(List<Integer> sorts,List<Integer> regions,int limit,int page);

    /**
     * 查询符合条件的视频数
     * @param sorts 分类ID数组
     * @param regions 属地ID数组
     * @return 符合条件的视频数
     */
    public int getAmount(List<Integer> sorts,List<Integer> regions);

    /**
     * 获得用户上传的ID
     * @param id 用户ID
     * @return 用户上传的视频列表
     */
    public List<U_Video> getUserUpload(int id);

    public Video mpSelectById(int id);
}
