package com.xiangge.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiangge.eduservice.entity.EduCourse;
import com.xiangge.eduservice.entity.frontvo.CourseWebVo;
import com.xiangge.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfo(String courseId);

    //根据课程id查询课程的基本信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
