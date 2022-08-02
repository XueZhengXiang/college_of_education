package com.xiangge.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 祥哥
 * @version 1.0
 */
@Component
@FeignClient(name = "service-order",fallback = OrderClientImpl.class)
public interface OrderClient {
    @GetMapping("/eduorder/paylog/isBuyCourse/{courseId}/{userId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("userId") String userId) ;
}
