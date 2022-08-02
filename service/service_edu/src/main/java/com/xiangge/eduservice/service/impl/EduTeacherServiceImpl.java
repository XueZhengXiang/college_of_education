package com.xiangge.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangge.eduservice.entity.EduTeacher;
import com.xiangge.eduservice.entity.vo.TeacherQuery;
import com.xiangge.eduservice.mapper.EduTeacherMapper;
import com.xiangge.eduservice.service.EduTeacherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 祥哥
 * @version 1.0
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public EduTeacher findById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void query(QueryWrapper<EduTeacher> wrapper, TeacherQuery teacherQuery) {
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        Date begin = teacherQuery.getBegin();
        Date end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);//大于等于这个时间的
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.like("gmt_modified", end);//小于等于这个时间的
        }
        wrapper.orderByDesc("gmt_create");
    }

    @Cacheable(key = "'selectHotTeacher'", value = "banner")
    @Override
    public List<EduTeacher> findHotTeacher() {
        QueryWrapper<EduTeacher> wrapper1 = new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 4");
        List<EduTeacher> teachers = baseMapper.selectList(wrapper1);
        return teachers;
    }

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(pageTeacher, wrapper);
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        List<EduTeacher> records = pageTeacher.getRecords();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        boolean next = pageTeacher.hasNext();
        boolean previous = pageTeacher.hasPrevious();
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
