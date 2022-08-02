package com.xiangge.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiangge.commonutils.R;
import com.xiangge.eduorder.entity.TOrder;
import com.xiangge.eduorder.service.TOrderService;
import com.xiangge.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
@RestController
@RequestMapping("/eduorder/paylog")
public class TPayLogController {
    @Autowired
    private TPayLogService payLogService;
    @Autowired
    private TOrderService orderService;

    //生成微信支付二维码
    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        //返回地址
        Map map = payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    //查询订单状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryStatus(orderNo);
        if (map == null) {
            return R.error().message("支付出错了");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            //添加到支付表,更新订单表状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功!!!");
        }
        return R.ok().code(25000).message("支付中!!");
    }

    //判断用户该视频是否需要购买
    @GetMapping("/isBuyCourse/{courseId}/{userId}")
    public boolean isBuyCourse(@PathVariable String courseId, @PathVariable String userId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", userId);
        wrapper.eq("status", 1);
        int count = orderService.count(wrapper);
        return count > 0;
    }
}

