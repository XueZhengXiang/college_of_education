package com.xiangge.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiangge.eduservice.entity.EduCourse;
import com.xiangge.eduservice.entity.frontvo.CourseVo;
import com.xiangge.eduservice.entity.frontvo.CourseWebVo;
import com.xiangge.eduservice.entity.vo.CourseInfoVo;
import com.xiangge.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

/**
 * @author 祥哥
 * @version 1.0
 */
public interface EduCourseService extends IService<EduCourse> {
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String courseId);

    void removeCourse(String courseId);

    List<EduCourse> findHotCourse();

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseVo courseVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
