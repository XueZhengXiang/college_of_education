package com.xiangge.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiangge.eduservice.entity.EduComment;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-27
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getCommentList(Page<EduComment> pageComment,String courseId);
}
