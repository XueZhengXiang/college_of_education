package com.xiangge.eduservice.controller.front;

import com.xiangge.commonutils.R;
import com.xiangge.eduservice.entity.EduCourse;
import com.xiangge.eduservice.entity.EduTeacher;
import com.xiangge.eduservice.service.EduCourseService;
import com.xiangge.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 祥哥
 * @version 1.0
 */
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，前4名讲师
    @GetMapping("/index")
    public R index() {
        List<EduCourse> eduCourseList=courseService.findHotCourse();
        List<EduTeacher> teacherList = teacherService.findHotTeacher();
        return R.ok().data("eduList", eduCourseList).data("teacherList", teacherList);
    }


}
