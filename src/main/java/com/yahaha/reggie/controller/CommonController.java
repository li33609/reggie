package com.yahaha.reggie.controller;

import com.yahaha.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Yahaha
 * @date 2022-08-23 15:09:05
 * @description 文件上传
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    // 自定义配置文件路径，在yaml文件中设置
    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        // file是一个临时文件，需要转存到指定位置，否则本次请求会消失
        log.info("文件名字{}:", file.toString());

        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 使用UUID重新生成文件名，防止文件名重复
        String fileName = UUID.randomUUID().toString() + suffix;

        // 创建一个目录对象
        File dir = new File(basePath);
        // 判断当前目录是否存在
        if( !dir.exists() ){
            // 目录不存在，创建目录
            dir.mkdirs();
        }

        try{
            // 将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        }catch (IOException e){
            e.printStackTrace();
        }

        return R.success(fileName);
    }
}
