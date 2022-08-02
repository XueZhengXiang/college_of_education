package com.xiangge.edusta.client;

import com.xiangge.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 祥哥
 * @version 1.0
 */
@Component
@FeignClient(name = "service-ucenter", fallback = UCenterClientImpl2.class)
public interface UCenterClient2 {
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
