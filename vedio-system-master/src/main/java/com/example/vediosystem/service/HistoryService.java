package com.example.vediosystem.service;


import com.example.vediosystem.dao.HistoryDao;
import com.example.vediosystem.domain.History;
import com.example.vediosystem.domain.V_History;

import java.util.List;

public interface HistoryService {

    /**
     * 获取该用户的历史记录
     * @param uId 用户ID
     * @param flag 标志位 0获取近一周 ， 1获取一个月 ， 2获取更早
     * @return 该用户的历史记录
     */
    public List<V_History> getUserHistory(int uId, int flag);

    /**
     * 增加一条历史记录
     * @param history 历史记录信息
     */
    public void addHistory(History history);

    /**
     * 更新历史记录
     * @param history 历史记录信息
     */
    public void updateHistory(History history);

    /**
     * 根据用户ID和视频ID 检索历史记录
     * @param uId
     * @param vId
     */
    public int selectHistory(int uId,int vId);

    /**
     * 根据用ID 检索历史记录
     * @param Id
     */
    public History selectHistoryById(int Id);
}
