package com.example.vediosystem.service.serviceImpl;

import com.example.vediosystem.controller.Code;
import com.example.vediosystem.dao.FavoritesDao;
import com.example.vediosystem.dao.FavoritesDetailDao;
import com.example.vediosystem.domain.Favorites;
import com.example.vediosystem.domain.FavoritesDetail;
import com.example.vediosystem.domain.U_Video;
import com.example.vediosystem.exception.BusinessException;
import com.example.vediosystem.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    FavoritesDetailDao favoritesDetailDao;

    @Autowired
    FavoritesDao favoritesDao;

    @Override
    public void addVideo(int uId,int vId) {
        Favorites favorites = favoritesDao.selectByUserId(uId);
        FavoritesDetail favoritesDetail = new FavoritesDetail();
        //判断是否重复收藏
        FavoritesDetail detail = favoritesDetailDao.getDetail(favorites.getId(), vId);
        if (detail!=null)throw new BusinessException(Code.SAVE_ERR,"该视频已存在收藏夹中");
        LocalDateTime now = LocalDateTime.now();
        favoritesDetail.setFavoritesId(favorites.getId());
        favoritesDetail.setVideoId(vId);
        favoritesDetail.setAddTime(now);
        int row = favoritesDetailDao.insert(favoritesDetail);
        if (row<1) throw new BusinessException(Code.SAVE_ERR,"保存失败");

    }

    @Override
    public List<U_Video> getUserFavorites(int uId) {
        Favorites favorites = favoritesDao.selectByUserId(uId);
        int favoritesId = favorites.getId();
        return favoritesDetailDao.getUserFavorites(favoritesId);
    }
}
