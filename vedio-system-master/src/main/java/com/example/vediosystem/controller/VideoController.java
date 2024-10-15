package com.example.vediosystem.controller;

import com.example.vediosystem.domain.U_Video;
import com.example.vediosystem.domain.Video;
import com.example.vediosystem.domain.VideoInfo;
import com.example.vediosystem.domain.VideoSelect;
import com.example.vediosystem.service.VideoService;
import com.example.vediosystem.utils.SaveFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    SaveFile saveFile;


    /**
     * 用户上传视频文件
     * @return
     */
    @PostMapping("/upload")
    public Result uploadVideo(@RequestParam("file") MultipartFile file) {
        String url = "D:/VideoSave/";
        System.out.println(file);
        Result result = saveFile.saveFile(file, url);
        //如果存储错误，返回Result
        if (Objects.equals(result.getCode(), Code.SAVE_ERR)) return result;
        String newFileName = (String) result.getData();

        //对存储完的文件进行切片
        String path = "D:/VideoSave/";
        Video video = null;
        try {
            video = videoService.ffmpegVideo(path,newFileName);
        }catch (Exception e) {
            return new Result(Code.CREATE_ERR,e.getMessage());
        }

        return new Result(Code.SAVE_OK,video,"存储文件完成" );
    }

    @PostMapping("/info")
    public Result uploadVideoInformation(@RequestBody VideoInfo video){
        videoService.uploadVideoInfo(video);
        return new Result(Code.SAVE_OK,"保存成功");
    }

    @PostMapping("/cover")
    public Result uploadCover(@RequestParam("file") MultipartFile file,@RequestParam("id") int id){
        String defaultUrl = "D:/VideoCover/";
        Result result = saveFile.saveFile(file, defaultUrl);
        //如果存储错误，返回Result
        if (Objects.equals(result.getCode(), Code.SAVE_ERR)) return result;
        String newFileName = (String) result.getData();
        //将视频封面存储进数据库
        videoService.updateCover(id,newFileName);
        return new Result(Code.SAVE_OK, newFileName,"保存成功");
    }

    /**
     * 用户获取封面图
     * @return 封面图
     */
    @GetMapping("/coverlook/{id}")
    public Result CoverLook (HttpServletResponse response, @PathVariable String id) {
        String filePath = "D:/VideoCover/"+id;
        File file = new File(filePath);
        byte[] bytes = new byte[1024];
        try (OutputStream os = response.getOutputStream();
             FileInputStream fis = new FileInputStream(file)){
            while ((fis.read(bytes)) != -1) {
                os.write(bytes);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(Code.SAVE_OK,"获取封面成功" );
    }


    /**
     * 用户上传视频后预览
     * @return 上传视频结果
     */
    @GetMapping("/preview/{id}")
    public Result videoLook (HttpServletResponse response, @PathVariable Integer id) {
        Video video = videoService.mpSelectById(id);
        String fileName = video.getLink();
        //得到不含后缀的文件名
        String nameNoSuffix = fileName.substring(0,fileName.lastIndexOf('.'));
        String newSuffix = ".mp4";
        String filePath = "D:/VideoSave/"+nameNoSuffix+newSuffix;
        File file = new File(filePath);
        byte[] bytes = new byte[1024];
        try (OutputStream os = response.getOutputStream();
             FileInputStream fis = new FileInputStream(file)){
            while ((fis.read(bytes)) != -1) {
                os.write(bytes);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(Code.SAVE_OK,"获取预览文件成功" );
    }

    /**
     * 用户获取视频流
     * @return m3u8以及ts文件
     */

    @GetMapping("/cloud/{id}")
    public Result GetVideo (HttpServletResponse response, @PathVariable String id) {
        System.out.println(id);
        String suffix = id.substring(id.lastIndexOf('.'));
        id = id.substring(0,id.lastIndexOf('.'));
        System.out.println(suffix);
        if(suffix.equals(".ts")){
            System.out.println("ts");
            String filePath = "D:/VideoSave/"+id+".ts";
            System.out.println(filePath);
            File file = new File(filePath);
            byte[] bytes = new byte[1024];
            try (OutputStream os = response.getOutputStream();
                 FileInputStream fis = new FileInputStream(file)){
                while ((fis.read(bytes)) != -1) {
                    os.write(bytes);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Video video = videoService.selectById(Integer.parseInt(id));
            String fileName = video.getLink();
            //得到不含后缀的文件名
            String nameNoSuffix = fileName.substring(0,fileName.lastIndexOf('.'));
            String filePath = "D:/VideoSave/"+fileName;
            System.out.println(filePath);
            File file = new File(filePath);
            byte[] bytes = new byte[1024];
            try (OutputStream os = response.getOutputStream();
                 FileInputStream fis = new FileInputStream(file)){
                while ((fis.read(bytes)) != -1) {
                    os.write(bytes);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return new Result(Code.SAVE_OK,"获取视频流成功" );
    }

    /**
     * 模糊搜索视频
     * @param name 视频标题
     * @return 符合搜索结果的结果项
     */
    @GetMapping("/get")
    public Result selectVideos(@RequestParam("name") String name){
        List<U_Video> videos = videoService.selectVideos(name);
        return new Result(Code.GET_OK,videos,"获取成功");
    }

    /**
     * 点赞视频
     * @param id 视频ID
     * @return  更新结果
     */
    @GetMapping("/like/{id}")
    public Result likeVideo(@PathVariable int id){
        videoService.likeVideo(id);
        return new Result(Code.UPDATE_OK,"更新成功");
    }

    /**
     * 增加播放量
     * @param id 视频ID
     * @return 操作结果
     */
    @GetMapping("/plays/{id}")
    public Result addVideoPlay(@PathVariable int id){
        videoService.addVideoPlays(id);
        return new Result(Code.UPDATE_OK,"更新成功");
    }

    @GetMapping("/{id}")
    public Result getVideoByID(@PathVariable int id){
        U_Video video = videoService.selectById(id);
        return new Result(Code.GET_OK,video,"获取成功");
    }

    @GetMapping("/pure/{id}")
    public Result getOnlyVideoByID(@PathVariable int id){
        Video video = videoService.mpSelectById(id);
        return new Result(Code.GET_OK,video,"获取成功");
    }

    @PostMapping("/mostLike")
    public Result getMostLikeVideos(@RequestBody VideoSelect videoSelect){
        List<U_Video> videos = videoService.getMLVideos(
                videoSelect.getSorts(),videoSelect.getRegions(),
                videoSelect.getLimit(),videoSelect.getPage());
        return new Result(Code.GET_OK,videos,"获取成功");
    }

    @PostMapping("/mostPlay")
    public Result getMostPlayVideos(@RequestBody VideoSelect videoSelect){
        List<U_Video> videos = videoService.getMPVideos(
                videoSelect.getSorts(),videoSelect.getRegions(),
                videoSelect.getLimit(),videoSelect.getPage());
        return new Result(Code.GET_OK,videos,"获取成功");
    }

    @PostMapping("/amount")
    public Result getAmount(@RequestBody VideoSelect videoSelect){
        int amount = videoService.getAmount(videoSelect.getSorts(), videoSelect.getRegions());
        return new Result(Code.GET_OK,amount,"获取成功");
    }

    @GetMapping("/userUpload/{id}")
    public Result userUploadVideos(@PathVariable int id){
        List<U_Video> userUpload = videoService.getUserUpload(id);
        return new Result(Code.GET_OK,userUpload,"获取成功");
    }
}
