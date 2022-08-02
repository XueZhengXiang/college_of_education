package com.xiangge.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiangge.commonutils.JwtUtils;
import com.xiangge.commonutils.R;
import com.xiangge.eduorder.entity.TOrder;
import com.xiangge.eduorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
@RestController
@RequestMapping("/eduorder/order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    //生成订单号
    @PostMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderNo=orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId",orderNo);
    }

    //根据订单号生成订单信息
    @GetMapping("/getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder one = orderService.getOne(wrapper);
        return R.ok().data("item",one);
    }
}

