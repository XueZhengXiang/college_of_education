package com.xiangge.eduservice.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangge.eduservice.entity.EduComment;
import com.xiangge.eduservice.mapper.EduCommentMapper;
import com.xiangge.eduservice.service.EduCommentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-27
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public Map<String, Object> getCommentList(Page<EduComment> pageComment,String courseId) {
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        //判断课程id是否为空
        if (!StringUtils.isEmpty(courseId)){
            wrapper.eq("course_id",courseId);
        }
        //按最新排序
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageComment, wrapper);
        long current = pageComment.getCurrent();
        long pages = pageComment.getPages();
        List<EduComment> records = pageComment.getRecords();
        long size = pageComment.getSize();
        long total = pageComment.getTotal();
        boolean next = pageComment.hasNext();
        boolean previous = pageComment.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", next);
        map.put("hasPrevioous", previous);
        return map;
    }
}
