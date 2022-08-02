package com.xiangge.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangge.eduservice.client.VodClient;
import com.xiangge.eduservice.entity.*;
import com.xiangge.eduservice.entity.frontvo.CourseVo;
import com.xiangge.eduservice.entity.frontvo.CourseWebVo;
import com.xiangge.eduservice.entity.vo.CourseInfoVo;
import com.xiangge.eduservice.entity.vo.CoursePublishVo;
import com.xiangge.eduservice.mapper.EduCourseMapper;
import com.xiangge.eduservice.service.EduChapterService;
import com.xiangge.eduservice.service.EduCourseDescriptionService;
import com.xiangge.eduservice.service.EduCourseService;
import com.xiangge.eduservice.service.EduVideoService;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    //注入章节
    @Autowired
    private EduChapterService eduChapterService;
    //注入小节
    @Autowired
    private EduVideoService eduVideoService;

    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            //添加失败
            throw new GuliException(20001, "添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    //根据课程id查看课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1查看课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        //1查看描述表
        EduCourseDescription description = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(description.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i == 0) {
            throw new GuliException(20001, "修改课程信息错误");
        }
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoVo.getDescription());
        description.setId(courseInfoVo.getId());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String courseId) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    @Autowired
    private VodClient vodClient;

    @Transactional
    @Override
    public void removeCourse(String courseId) {
        //1、根据课程id删除小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
//        wrapper.select("video_source_id");

        List<EduVideo> videoList = eduVideoService.list(wrapper);
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo eduVideo = videoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoIds.add(videoSourceId);
            }
        }
        //调用openfeign删除课程下的多个视频
        if (videoIds.size() > 0) {
            vodClient.deleteBatch(videoIds);
        }
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id", courseId);
        eduVideoService.remove(wrapper1);

        //2、根据课程id删除章节
        QueryWrapper<EduChapter> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id", courseId);
        eduChapterService.remove(wrapper2);
        //3、根据课程id删除描述
        QueryWrapper<EduCourseDescription> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("id", courseId);
        courseDescriptionService.remove(wrapper3);
        //4、根据课程id删除课程本身
        int i = baseMapper.deleteById(courseId);
        if (i == 0) {
            throw new GuliException(20001, "删除课程失败!");
        }


    }

    @Cacheable(key = "'selectHotCourse'", value = "banner")
    @Override
    public List<EduCourse> findHotCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduCourseList = baseMapper.selectList(wrapper);
        return eduCourseList;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseVo courseVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseVo.getSubjectId())){
            wrapper.eq("subject_id",courseVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseVo.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseVo.getPriceSort())){
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageCourse,wrapper);
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        List<EduCourse> records = pageCourse.getRecords();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean next = pageCourse.hasNext();
        boolean previous = pageCourse.hasPrevious();
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

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {

        return baseMapper.getBaseCourseInfo(courseId);
    }
}
