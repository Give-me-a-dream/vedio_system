package com.example.vediosystem;

import com.example.vediosystem.dao.BarrageDao;
import com.example.vediosystem.dao.CommentDao;
import com.example.vediosystem.dao.HistoryDao;
import com.example.vediosystem.dao.VideoDao;
import com.example.vediosystem.domain.*;
import com.example.vediosystem.service.CommentService;
import com.example.vediosystem.service.UserService;
import com.example.vediosystem.service.VideoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VedioSystemApplicationTests {

    @Autowired
    VideoService videoService;

    @Autowired
    VideoDao videoDao;

    @Autowired
    UserService userService;

    @Autowired
    CommentDao commentDao;

    @Autowired
    CommentService commentService;

    @Autowired
    HistoryDao historyDao;

    @Autowired
    BarrageDao barrageDao;

    @Test
    void contextLoads() {
    }


    @Test
    void testGetComment(){
        int id = 4;
        List<U_Comment> u_comments = commentService.getHotComments(4,3);

        for (U_Comment u:u_comments
             ) {
            System.out.println(u.getLikes());
        }
    }

    @Test
    void testGetCommentCount(){
        int id = 4;
        Integer page = commentService.getVideoCommentPage(id);
        System.out.println(page);
    }


    @Test
    void tset(){
        List<Barrage> all = barrageDao.findAll("13");
        System.out.println(all);
    }
}

