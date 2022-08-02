package com.xiangge.eduorder.service;

import com.xiangge.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createNative(String orderNo);

    Map<String, String> queryStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
