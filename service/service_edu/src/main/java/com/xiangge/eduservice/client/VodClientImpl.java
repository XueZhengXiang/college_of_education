package com.xiangge.eduservice.client;

import com.xiangge.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 祥哥
 * @version 1.0
 * openfeign出错后执行
 */
@Component
public class VodClientImpl implements VodClient{
    @Override
    public R removeAlyVideo(String videoSourceId) {
        return R.error().message("删除小节下视频出错!!!");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return R.error().message("删除课程下视频出错!!!");
    }
}
