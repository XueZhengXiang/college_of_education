package com.xiangge.eduservice.controller;


import com.xiangge.commonutils.R;
import com.xiangge.eduservice.client.VodClient;
import com.xiangge.eduservice.entity.EduVideo;
import com.xiangge.eduservice.service.EduVideoService;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        boolean save = videoService.save(eduVideo);
        return save ? R.ok() : R.error();
    }

    //openfeign第4步
    @Autowired
    private VodClient vodClient;

    //删除小节
    //TODO 后面完善把阿里云视频删除
    @DeleteMapping("/{id}")
    public R deleteVideo(@PathVariable String id) {
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //通过openFeign删除阿里云视频
        if (!StringUtils.isEmpty(videoSourceId)) {
            R r = vodClient.removeAlyVideo(videoSourceId);
                if (!r.getSuccess()) {
                throw new GuliException(20001, "删除视频出错");
            }
        }
        boolean b = videoService.removeById(id);
        return b ? R.ok() : R.error();
    }


}

