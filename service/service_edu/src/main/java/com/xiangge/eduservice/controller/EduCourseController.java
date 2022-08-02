package com.xiangge.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiangge.commonutils.R;
import com.xiangge.eduservice.entity.EduCourse;
import com.xiangge.eduservice.entity.vo.CourseInfoVo;
import com.xiangge.eduservice.entity.vo.CoursePublishVo;
import com.xiangge.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息的方法
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    //添加课程基本信息的方法
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        //返回添加之后课程id，为了后面添加大纲使用
        CourseInfoVo courseInfo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfo);
    }

    //修改课程基本信息的方法
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据id确认信息getPublishCourseInfo
    @GetMapping("/getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId) {
        CoursePublishVo courseInfo = courseService.publishCourseInfo(courseId);
        return R.ok().data("publishCourse", courseInfo);
    }

    //课程最终发布
    @PostMapping("/publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId) {
        //返回添加之后课程id，为了后面添加大纲使用
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus(EduCourse.NORMAL);
        courseService.updateById(eduCourse);
        return R.ok();
    }

    @GetMapping()
    public R getListCourse() {
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        List<EduCourse> list = courseService.list(wrapper);
        return R.ok().data("list", list);
    }

    //删除课程
    @PostMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }


}