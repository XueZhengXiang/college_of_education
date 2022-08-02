package com.xiangge.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.xiangge.commonutils.R;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import com.xiangge.vod.service.VodService;
import com.xiangge.vod.utils.ConstantVodUtils;
import com.xiangge.vod.utils.InitVod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 祥哥
 * @version 1.0
 */
@RestController
@RequestMapping("/eduvod/video")
@Api("阿里云视频")
public class VodController {
    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频到阿里云")
    @PostMapping("/uploadAlyiVideo")
    public R uploadAliYun(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return videoId == null ? R.error() : R.ok().data("videoId", videoId);
    }

    @ApiOperation("删除小节下单个视频")
    @DeleteMapping("/removeAlyVideo/{videoSourceId}")
    public R removeAlyVideo(@PathVariable String videoSourceId) {
        try {
            DefaultAcsClient client = InitVod.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoSourceId);
            //调用初始化对象方法删除
            client.getAcsResponse(request);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败！！！");
        }
    }

    //删除多个阿里云视频
    @DeleteMapping("/delete-batch")
    public R deleteBatch(@RequestParam("videoList") List<String> videoList) {
        vodService.removeMoreAlyVideo(videoList);
        return R.ok();
    }

    //获取视频凭证
    @GetMapping("/getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {
        try {
        //创建初始化对象
            DefaultAcsClient client = InitVod.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证的request和response对象
            GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();

            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"根据视频id获取视频凭证失败!!!");
        }

    }











}
