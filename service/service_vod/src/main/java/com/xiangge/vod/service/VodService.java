package com.xiangge.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 祥哥
 * @version 1.0
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoList);
}
