package com.example.vediosystem.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.vediosystem.controller.Code;
import com.example.vediosystem.dao.VideoDao;
import com.example.vediosystem.dao.VideoSortDao;
import com.example.vediosystem.domain.*;
import com.example.vediosystem.exception.BusinessException;
import com.example.vediosystem.service.UserService;
import com.example.vediosystem.service.VideoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoDao videoDao;

    @Autowired
    UserService userService;

    @Autowired
    VideoSortDao videoSortDao;

    @Override
    public Video ffmpegVideo(String path,String fileName) throws IOException {
        //得到不含后缀的文件名
        String nameNoSuffix = fileName.substring(0,fileName.lastIndexOf('.'));
        // 执行的命令 例： ffmpeg -i E:\movie.mp4 -strict -2 -hls_list_size 0 -y E:\video.m3u8
        BufferedReader br = null;
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("ffmpeg","-i",path+fileName,"-strict","-2","-hls_list_size","0","-y",
                path+nameNoSuffix+".m3u8");
        // 将子进程的标准错误输出重定向到与标准输出流一起
        builder.redirectErrorStream(true);
        Process process = builder.start();
        // process.getInputStream返回一个输入流，用于读取由子进程的标准输出所产生的数据
        br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        //将存储的视频在数据库插入一条记录
        Video video = new Video();
        video.setLink(nameNoSuffix+".m3u8");
        video.setVName(nameNoSuffix);
        int rows = videoDao.insert(video);
        if (rows < 1) throw new BusinessException(Code.SAVE_ERR,"添加数据失败");

        //获得插入后的表中的实体类，返回自动生成的ID
        return this.selectIdByName(nameNoSuffix);

    }

    @Override
    public Video selectIdByName(String name) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("v_name",name);
        return videoDao.selectOne(queryWrapper);
    }

    @Override
    public void uploadVideoInfo(VideoInfo video) {
        //获得存储视频自动生成的视频类，根据传入的信息补全该类
        Video video1 = this.mpSelectById(video.getId());
        LocalDateTime now = LocalDateTime.now(); // 获取当前时间
        video1.setUploaderTime(now);
        video1.setUploaderId(video.getUploaderId());
        video1.setTitle(video.getTitle());
        video1.setRegionId(video.getRegionId());

        //更新此视频信息
        int rows = videoDao.updateById(video1);
        if (rows<1) throw new BusinessException(Code.SAVE_ERR,"数据保存失败");

        //更新视频分类表
        VideoSort videoSort = new VideoSort();
        videoSort.setVideoId(video1.getId());
        videoSort.setSortId(video.getSortId());
        int i = videoSortDao.insert(videoSort);
        if (i<1) throw new BusinessException(Code.SAVE_ERR,"数据保存失败");
    }

    @Override
    public U_Video selectById(int id) {

        return videoDao.selectUVideoById(id);
    }

    @Override
    public void updateCover(int id, String url) {
        //获得存储视频自动生成的视频类，根据传入的信息补全该类
        Video video = this.selectById(id);
        video.setPicUrl(url);
        int rows = videoDao.updateById(video);
        if (rows<1) throw new BusinessException(Code.SAVE_ERR,"数据保存失败");
    }

    @Override
    public List<U_Video> selectVideos(String name) {
        return videoDao.selectVideos(name);
    }

    @Override
    public void likeVideo(int id) {
        int row = videoDao.likeVideo(id);
        if (row<1)throw new  BusinessException(Code.UPDATE_ERR,"更新失败");
    }

    @Override
    public void addVideoPlays(int id) {
        int row = videoDao.addVideoPlays(id);
        if (row<1)throw new  BusinessException(Code.UPDATE_ERR,"更新失败");
    }

    @Override
    public List<U_Video> getMPVideos(List<Integer> sorts, List<Integer> regions, int limit, int page) {
        return videoDao.getMPVideos(sorts, regions, limit, (page - 1) * limit);
    }

    @Override
    public List<U_Video> getMLVideos(List<Integer> sorts, List<Integer> regions, int limit, int page) {
        return videoDao.getMLVideos(sorts, regions, limit, (page - 1) * limit);
    }

    @Override
    public int getAmount(List<Integer> sorts, List<Integer> regions) {
        return  videoDao.getAmount(sorts,regions);
    }

    @Override
    public List<U_Video> getUserUpload(int id) {
        return videoDao.getUserUpload(id);
    }

    @Override
    public Video mpSelectById(int id) {
        return videoDao.selectById(id);
    }
}
