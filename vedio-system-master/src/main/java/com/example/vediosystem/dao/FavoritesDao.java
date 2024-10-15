package com.example.vediosystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vediosystem.domain.Favorites;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FavoritesDao extends BaseMapper<Favorites> {

    @Select("SELECT * FROM t_favorites where u_id = #{uId}")
    Favorites selectByUserId(@Param("uId") int uId);
}
