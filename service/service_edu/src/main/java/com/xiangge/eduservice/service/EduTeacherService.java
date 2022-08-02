package com.xiangge.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiangge.eduservice.entity.EduTeacher;
import com.xiangge.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * @author 祥哥
 * @version 1.0
 */
public interface EduTeacherService extends IService<EduTeacher> {
EduTeacher findById(String id);

    void query(QueryWrapper<EduTeacher> wrapper,TeacherQuery teacherQuery);

    List<EduTeacher> findHotTeacher();

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
