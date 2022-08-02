package com.xiangge.eduorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangge.commonutils.ordervo.CourseWebOrder;
import com.xiangge.commonutils.ordervo.UcenterMemberOrder;
import com.xiangge.eduorder.client.CourseClient;
import com.xiangge.eduorder.client.UcenterClient;
import com.xiangge.eduorder.entity.TOrder;
import com.xiangge.eduorder.mapper.TOrderMapper;
import com.xiangge.eduorder.service.TOrderService;
import com.xiangge.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private CourseClient courseClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberIdByJwtToken) {
        // 1、生成课程基本信息
        CourseWebOrder courseInfoOrder = courseClient.getCourseInfoOrder(courseId);

        //2、生成下单人信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);
        TOrder order = new TOrder();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setCourseId(courseInfoOrder.getId());//课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(userInfoOrder.getId());
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return orderNo;
    }
}
