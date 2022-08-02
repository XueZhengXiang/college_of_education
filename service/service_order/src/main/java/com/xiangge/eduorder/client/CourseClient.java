package com.xiangge.eduorder.client;

import com.xiangge.commonutils.ordervo.CourseWebOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 祥哥
 * @version 1.0
 */
@Component
@FeignClient(name = "service-edu",fallback = CourseClientImpl.class)
public interface CourseClient {
    @PostMapping("/eduservice/couesefront/getCourseInfoOrder/{id}")
    public CourseWebOrder getCourseInfoOrder(@PathVariable("id") String id);
}
