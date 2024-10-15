package com.example.vediosystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vediosystem.domain.U_Video;
import com.example.vediosystem.domain.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VideoDao extends BaseMapper<Video> {

    //根据名字模糊查询视频信息
    @Select("""
        SELECT a.id, a.uploader_id, a.v_name, a.uploader_time, a.link, a.plays, a.likes, a.title, a.pic_url,
               b.prinvince_name, b.introduce, c.name AS uploader_name
        FROM t_vedio a
        JOIN t_region b ON a.region_id = b.id
        JOIN t_user c ON a.uploader_id = c.id
        WHERE a.title LIKE CONCAT('%', #{name}, '%')
        """)
    List<U_Video> selectVideos(@Param("name") String name);
    //给指定ID的视频点赞
   @Update("""
        UPDATE t_vedio 
        SET likes = likes + 1 
        WHERE id = #{id}
        """)
        int likeVideo(@Param("id") int id);
    
        //给指定ID的视频增加播放量
        @Update("""
        UPDATE t_vedio 
        SET plays = plays + 1 
        WHERE id = #{id}
        """)
    int addVideoPlays(@Param("id") int id);

    /**
     * 根据分类和属地分页查询视频，以播放量排序
     * @param sorts 分类ID数组
     * @param regions 属地ID数组
     * @param limit 限制每页数量
     * @param offset 跳过记录条数
     * @return 符合条件的视频数组
     */
   @Select("""
        <script>
        SELECT a.id, a.region_id, a.uploader_id, a.v_name, a.uploader_time, a.link, a.plays, a.likes, a.title, a.pic_url,
               b.prinvince_name, b.introduce, c.name AS uploader_name
        FROM t_vedio a
        JOIN t_region b ON a.region_id = b.id
        JOIN t_user c ON a.uploader_id = c.id
        WHERE a.id IN (
            SELECT vedio_id 
            FROM t_vedio_sorts 
            WHERE sort_id IN 
            <foreach collection='sorts' item='sort' open='(' separator=',' close=')'>
                #{sort}
            </foreach>
        ) 
        AND a.region_id IN 
        <foreach collection='regions' item='region' open='(' separator=',' close=')'>
            #{region}
        </foreach>
        ORDER BY a.plays DESC
        LIMIT #{limit} OFFSET #{offset}
        </script>
        """)
    List<U_Video> getMPVideos(@Param("sorts") List<Integer> sorts, @Param("regions") List<Integer> regions, 
                              @Param("limit") int limit, @Param("offset") int offset);
    /**
     * 根据分类和属地分页查询视频，以点赞排序
     * @param sorts 分类ID数组
     * @param regions 属地ID数组
     * @param limit 限制每页数量
     * @param offset 跳过记录条数
     * @return 符合条件的视频数组
     */
    @Select("""
        <script>
        SELECT a.id, a.region_id, a.uploader_id, a.v_name, a.uploader_time, a.link, a.plays, a.likes, a.title, a.pic_url,
               b.prinvince_name, b.introduce, c.name AS uploader_name
        FROM t_vedio a
        JOIN t_region b ON a.region_id = b.id
        JOIN t_user c ON a.uploader_id = c.id
        WHERE a.id IN (
            SELECT vedio_id 
            FROM t_vedio_sorts 
            WHERE sort_id IN 
            <foreach collection='sorts' item='sort' open='(' separator=',' close=')'>
                #{sort}
            </foreach>
        ) 
        AND a.region_id IN 
        <foreach collection='regions' item='region' open='(' separator=',' close=')'>
            #{region}
        </foreach>
        ORDER BY a.likes DESC
        LIMIT #{limit} OFFSET #{offset}
        </script>
        """)
    List<U_Video> getMLVideos(@Param("sorts") List<Integer> sorts, @Param("regions") List<Integer> regions, 
                              @Param("limit") int limit, @Param("offset") int offset);

    //获取视频总数
    @Select("""
        <script>
        SELECT COUNT(*) 
        FROM t_vedio 
        WHERE id IN (
            SELECT vedio_id 
            FROM t_vedio_sorts 
            WHERE sort_id IN 
            <foreach collection='sorts' item='sort' open='(' separator=',' close=')'>
                #{sort}
            </foreach>
        ) 
        AND region_id IN 
        <foreach collection='regions' item='region' open='(' separator=',' close=')'>
            #{region}
        </foreach>
        </script>
        """)
    int getAmount(@Param("sorts") List<Integer> sorts, @Param("regions") List<Integer> regions);

    //根据视频ID查询视频信息
    @Select("""
        SELECT a.id, a.uploader_id, a.v_name, a.uploader_time, a.link, a.plays, a.likes, a.title, a.pic_url,
               b.prinvince_name, b.introduce, c.name AS uploader_name
        FROM t_vedio a
        JOIN t_region b ON a.region_id = b.id
        JOIN t_user c ON a.uploader_id = c.id
        WHERE a.id = #{id}
        """)
    U_Video selectUVideoById(@Param("id") int id);

    //根据用户ID查询用户上传的视频
    @Select("""
        SELECT a.id, a.v_name, a.uploader_id, a.uploader_time, a.link, a.plays, a.likes, a.title, a.pic_url,
               b.prinvince_name, b.introduce, c.name AS uploader_name
        FROM t_vedio a
        JOIN t_region b ON a.region_id = b.id
        JOIN t_user c ON a.uploader_id = c.id
        WHERE a.uploader_id = #{id}
        """)
    List<U_Video> getUserUpload(@Param("id") int id);
}


