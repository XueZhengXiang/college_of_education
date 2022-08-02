package com.xiangge.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiangge.commonutils.JwtUtils;
import com.xiangge.commonutils.R;
import com.xiangge.commonutils.ordervo.CourseWebOrder;
import com.xiangge.eduservice.client.OrderClient;
import com.xiangge.eduservice.entity.EduCourse;
import com.xiangge.eduservice.entity.chapter.ChapterVo;
import com.xiangge.eduservice.entity.frontvo.CourseVo;
import com.xiangge.eduservice.entity.frontvo.CourseWebVo;
import com.xiangge.eduservice.service.EduChapterService;
import com.xiangge.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 祥哥
 * @version 1.0
 */
@RestController
@RequestMapping("/eduservice/couesefront")
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    //条件查询课程信息
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable Long page, @PathVariable Long limit,
                                @RequestBody(required = false) CourseVo courseVo) {
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseVo);
        return R.ok().data(map);
    }

    //获取课程详情
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {

        //1、根据课程ID，编写SQL语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //2、根据课程ID，查看课程章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        //3、根据课程id和用户id查询当前课程是否已经支付
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean isBuy = orderClient.isBuyCourse(courseId, memberId);
        return R.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuy", isBuy);
    }

    @PostMapping("/getCourseInfoOrder/{id}")
    public CourseWebOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(id);
        CourseWebOrder courseWebOrder = new CourseWebOrder();
        BeanUtils.copyProperties(baseCourseInfo, courseWebOrder);
        return courseWebOrder;
    }
}
