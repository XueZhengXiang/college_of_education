package com.xiangge.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 祥哥
 * @version 1.0
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file) throws IOException;
}
