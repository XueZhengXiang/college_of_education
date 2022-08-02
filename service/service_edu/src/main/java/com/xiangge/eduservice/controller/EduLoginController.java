package com.xiangge.eduservice.controller;

import com.xiangge.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author 祥哥
 * @version 1.0
 */
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("/info")
public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://img0.baidu.com/it/u=2950970616,2356748823&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=496");
    }
}
