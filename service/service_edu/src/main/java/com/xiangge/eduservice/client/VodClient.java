package com.xiangge.eduservice.client;

import com.xiangge.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 祥哥
 * @version 1.0
 */
//第2步
@Component
@FeignClient(name = "service-vod", fallback = VodClientImpl.class)
public interface VodClient {
    //第3步
    @DeleteMapping("/eduvod/video/removeAlyVideo/{videoSourceId}")
    public R removeAlyVideo(@PathVariable("videoSourceId") String videoSourceId);

    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoList") List<String> videoList);

}
