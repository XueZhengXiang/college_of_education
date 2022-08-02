package com.xiangge.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author 祥哥
 * @version 1.0
 */
@Component
public class OrderClientImpl implements OrderClient{

    @Override
    public boolean isBuyCourse(String courseId, String userId) {
        return false;
    }
}
