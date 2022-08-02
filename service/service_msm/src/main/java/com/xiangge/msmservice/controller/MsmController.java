package com.xiangge.msmservice.controller;

import com.xiangge.commonutils.R;
import com.xiangge.msmservice.service.MsmService;
import com.xiangge.msmservice.util.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信验证登录
 * @author 祥哥
 * @version 1.0
 */
@RestController
@RequestMapping("/edumsm/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send/{phone}")
    public R setMsm(@PathVariable String phone) {
        //首先从redis获取验证码
        String value = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(value)) {
            return R.ok();
        }
        //生成随机值发送给阿里云短信
        String code = RandomUtil.getFourBitRandom();
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        //调用发送短信方法
        boolean b = msmService.send(map, phone);
        if (b) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败!!!");
        }

    }
}
