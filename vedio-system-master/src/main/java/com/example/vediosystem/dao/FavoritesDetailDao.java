package com.example.vediosystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vediosystem.domain.FavoritesDetail;
import com.example.vediosystem.domain.U_Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoritesDetailDao extends BaseMapper<FavoritesDetail> {

    @Select("""
    SELECT a.id,
           a.v_name,
           a.uploader_time,
           a.link,
           a.plays,
           a.likes,
           a.title,
           a.pic_url,
           b.prinvince_name,
           b.introduce, 
           c.name AS uploader_name
    FROM t_vedio a
    JOIN t_region b ON a.region_id = b.id
    JOIN t_user c ON a.uploader_id = c.id
    JOIN t_favorites_detail d ON d.vedio_id = a.id
    WHERE d.favorites_id = #{favoritesId}
    ORDER BY d.add_time DESC
    """)
    List<U_Video> getUserFavorites(@Param("favoritesId") int favoritesId);


    @Select("SELECT * from t_favorites_detail where favorites_id = #{favoritesId} and vedio_id = #{videoId}")
    FavoritesDetail getDetail(@Param("favoritesId") int favoritesId,@Param("videoId") int videoId);
}
