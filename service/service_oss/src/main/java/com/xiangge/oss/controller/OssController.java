package com.xiangge.oss.controller;

import com.xiangge.commonutils.R;
import com.xiangge.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 头像封面OSS存储
 * @author 祥哥
 * @version 1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping
    public R uploadFile(MultipartFile file) throws IOException {
        String url=ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
