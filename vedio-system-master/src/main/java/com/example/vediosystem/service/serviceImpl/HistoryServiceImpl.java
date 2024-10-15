package com.example.vediosystem.service.serviceImpl;

import com.example.vediosystem.controller.Code;
import com.example.vediosystem.dao.HistoryDao;
import com.example.vediosystem.domain.History;
import com.example.vediosystem.domain.V_History;
import com.example.vediosystem.exception.BusinessException;
import com.example.vediosystem.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryDao historyDao;

    //flag 标志位 0获取近一周 ， 1获取一个月 ， 2获取更早
    @Override
    public List<V_History> getUserHistory(int uId, int flag) {
        List<V_History> histories = null;
        if (flag!=0&&flag!=1&&flag!=2)throw new BusinessException(Code.GET_ERR,"标志位错误");
        if (flag==0){
            histories = historyDao.selectUserHistoryWeek(uId);
        }else if (flag==1){
            histories = historyDao.selectUserHistoryMonth(uId);
        } else {
            histories = historyDao.selectUserHistoryLonger(uId);
        }
        return histories;
    }

    @Override
    public void addHistory(History history) {
        LocalDateTime now = LocalDateTime.now(); // 获取当前时间
        history.setWatchTime(now);
        int id = historyDao.selectHistory(history.getUserId(),history.getVideoId());
        int row = 0;
        if(id==0){
            row = historyDao.insert(history);
        }else{
            history.setId(id);
            History history1 = historyDao.selectById(id);
            history.setHistoryTime(history1.getHistoryTime());
            row = historyDao.updateById(history);
        }

        if (row<1)throw new BusinessException(Code.SAVE_ERR,"保存错误");
    }

    @Override
    public void updateHistory(History history) {
        LocalDateTime now = LocalDateTime.now(); // 获取当前时间
        history.setWatchTime(now);
        int id = historyDao.selectHistory(history.getUserId(),history.getVideoId());
        history.setId(id);
        int row = historyDao.updateById(history);
        if (row<1)throw new BusinessException(Code.SAVE_ERR,"保存错误");
    }

    @Override
    public int selectHistory(int uId,int vId) {
        int id = historyDao.selectHistory(uId,vId);
        System.out.println(id);
        return id;
    }

    @Override
    public History selectHistoryById(int Id) {
        return historyDao.selectById(Id);
    }


}
