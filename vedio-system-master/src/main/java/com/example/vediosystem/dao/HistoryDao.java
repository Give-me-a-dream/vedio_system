package com.example.vediosystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vediosystem.domain.History;
import com.example.vediosystem.domain.V_History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryDao extends BaseMapper<History> {
    // 查询过去一周的浏览历史
@Select("""
    SELECT d.id,
           a.id AS video_id,
           a.v_name,
           a.uploader_time,
           a.link,
           a.plays,
           a.likes,
           a.title,
           a.pic_url,
           b.prinvince_name,
           b.introduce, 
           c.name AS uploader_name,
           d.watch_time,
           d.history_time
    FROM t_vedio a
    JOIN t_region b ON a.region_id = b.id
    JOIN t_user c ON a.uploader_id = c.id
    JOIN t_browsing_history d ON d.vedio_id = a.id
    WHERE d.u_id = #{uId}
      AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(d.watch_time)
    """)
public List<V_History> selectUserHistoryWeek(@Param("uId") int uId);

// 查询过去一个月的浏览历史（不包括最近7天）
@Select("""
    SELECT d.id,
           a.id AS video_id,
           a.v_name,
           a.uploader_time,
           a.link,
           a.plays,
           a.likes,
           a.title,
           a.pic_url,
           b.prinvince_name,
           b.introduce, 
           c.name AS uploader_name,
           d.watch_time,
           d.history_time
    FROM t_vedio a
    JOIN t_region b ON a.region_id = b.id
    JOIN t_user c ON a.uploader_id = c.id
    JOIN t_browsing_history d ON d.vedio_id = a.id
    WHERE d.u_id = #{uId}
      AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) > DATE(d.watch_time)
      AND DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= DATE(d.watch_time)
    """)
public List<V_History> selectUserHistoryMonth(@Param("uId") int uId);

// 查询超过一个月的浏览历史
@Select("""
    SELECT d.id,
           a.id AS video_id,
           a.v_name,
           a.uploader_time,
           a.link,
           a.plays,
           a.likes,
           a.title,
           a.pic_url,
           b.prinvince_name,
           b.introduce, 
           c.name AS uploader_name,
           d.watch_time,
           d.history_time
    FROM t_vedio a
    JOIN t_region b ON a.region_id = b.id
    JOIN t_user c ON a.uploader_id = c.id
    JOIN t_browsing_history d ON d.vedio_id = a.id
    WHERE d.u_id = #{uId}
      AND DATE_SUB(CURDATE(), INTERVAL 30 DAY) > DATE(d.watch_time)
    """)
public List<V_History> selectUserHistoryLonger(@Param("uId") int uId);

// 根据UID和VID查找History.id
@Select("""
    SELECT IFNULL(MAX(id), 0) AS id
    FROM t_browsing_history
    WHERE vedio_id = #{vId}
      AND u_id = #{uId}
    """)
public int selectHistory(@Param("uId") int uId, @Param("vId") int vId);

}
