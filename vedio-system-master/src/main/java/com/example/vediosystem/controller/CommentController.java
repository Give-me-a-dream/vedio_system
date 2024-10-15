package com.example.vediosystem.controller;

import com.example.vediosystem.domain.Comment;
import com.example.vediosystem.domain.U_Comment;
import com.example.vediosystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 获取视频的热门评论
     * @param videoId 视频ID
     * @param page 第几页的评论（分页查询，10条评论一页，从1开始)
     * @return 返回10条评论记录
     */
    @GetMapping("/hot/{videoId}/{page}")
    public Result getHotComments(@PathVariable Integer videoId,@PathVariable Integer page){
        List<U_Comment> hotComments = commentService.getHotComments(videoId, page);
        if (hotComments == null) return new Result(Code.GET_ERR,"无信息");
        return new Result(Code.GET_OK,hotComments,"获取成功");
    }

    /**
     * 获取视频的最新评论
     * @param videoId 视频ID
     * @param page 第几页的评论（分页查询，10条评论一页，从1开始)
     * @return 返回10条评论记录
     */
    @GetMapping("/new/{videoId}/{page}")
    public Result getNewComments(@PathVariable Integer videoId,@PathVariable Integer page){
        List<U_Comment> hotComments = commentService.getNewComments(videoId, page);
        if (hotComments == null) return new Result(Code.GET_ERR,"无信息");
        return new Result(Code.GET_OK,hotComments,"获取成功");
    }

    /**
     * 获取评论页数
     * @param videoId 视频ID
     * @return 页数[int]
     */
    @GetMapping("/page/{videoId}")
    public Result getVideoPage(@PathVariable Integer videoId){
        Integer page = commentService.getVideoCommentPage(videoId);
        return new Result(Code.GET_OK,page,"获取成功");
    }

    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return new Result(Code.SAVE_OK,"保存成功");
    }

    @GetMapping("/like/{id}")
    public Result likeComment(@PathVariable int id){
        commentService.likeComment(id);
        return new Result(Code.UPDATE_OK,"更新成功");
    }

    @GetMapping("/unlike/{id}")
    public Result unlikeComment(@PathVariable int id){
        commentService.dislikeComment(id);
        return new Result(Code.UPDATE_OK,"更新成功");
    }
}
