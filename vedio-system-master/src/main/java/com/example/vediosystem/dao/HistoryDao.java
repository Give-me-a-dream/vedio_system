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
    @Select("SELECT d.id ,a.`id` as video_id,a.v_name,a.uploader_time,a.link,a.plays,a.likes,a.title,a.pic_url," +
            "b.prinvince_name,b.introduce, c.`name` as uploader_name,d.watch_time,d.history_time FROM t_vedio a,t_region b," +
            "t_user c,t_browsing_history d WHERE d.vedio_id = a.id and a.uploader_id=c.id and a.region_id=b.id and  d.u_id = #{uId} AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(d.watch_time)")
    public List<V_History> selectUserHistoryWeek(@Param("uId") int uId);

    @Select("SELECT d.id ,a.`id` as video_id,a.v_name,a.uploader_time,a.link,a.plays,a.likes,a.title,a.pic_url," +
            "b.prinvince_name,b.introduce, c.`name` as uploader_name,d.watch_time,d.history_time FROM t_vedio a,t_region b," +
            "t_user c,t_browsing_history d WHERE d.vedio_id = a.id and a.uploader_id=c.id and a.region_id=b.id and  d.u_id = #{uId} AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) > date(d.watch_time) AND DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(d.watch_time)")
    public List<V_History> selectUserHistoryMonth(@Param("uId") int uId);

    @Select("SELECT d.id ,a.`id` as video_id,a.v_name,a.uploader_time,a.link,a.plays,a.likes,a.title,a.pic_url," +
            "b.prinvince_name,b.introduce, c.`name` as uploader_name,d.watch_time,d.history_time FROM t_vedio a,t_region b," +
            "t_user c,t_browsing_history d WHERE d.vedio_id = a.id and a.uploader_id=c.id and a.region_id=b.id and  d.u_id = #{uId} AND DATE_SUB(CURDATE(), INTERVAL 30 DAY) > date(d.watch_time)")
    public List<V_History> selectUserHistoryLonger(@Param("uId") int uId);


    /**
     * 根据UID和VID查找History.id
     * @param uId
     * @param vId
     * @return History.id
     */
    @Select("SELECT\n" +
            "\tIFNULL(MAX(id),0) as id \n" +
            "FROM\n" +
            "\tt_browsing_history \n" +
            "WHERE\n" +
            "\tvedio_id = #{vId} \n" +
            "\tAND u_id = #{uId}")
    public int selectHistory(@Param("uId") int uId,@Param("vId") int vId) ;

}
