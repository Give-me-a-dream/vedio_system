package com.example.vediosystem.utils;

import com.example.vediosystem.controller.Code;
import com.example.vediosystem.controller.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Component
public class SaveFile {
    public Result saveFile(MultipartFile file,String defaultUrl){
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = new Date().getTime() + suffix;

        BufferedOutputStream stream = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                stream = new BufferedOutputStream(Files.newOutputStream(Paths.get(defaultUrl+newFileName))) ;
                //System.out.println(Arrays.toString(bytes));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                return new Result(Code.SAVE_ERR,
                        "You failed to upload => " + e.getMessage());
            }
        } else {
            return new Result(Code.SAVE_ERR,
                    "You failed to upload because the file was empty.");
        }

        System.out.println("存储文件完成！！");
        return new Result(Code.SAVE_OK,newFileName,"存储文件完成");
    }
}
