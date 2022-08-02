package com.xiangge.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiangge.commonutils.R;
import com.xiangge.eduservice.entity.EduTeacher;
import com.xiangge.eduservice.entity.vo.TeacherQuery;
import com.xiangge.eduservice.service.EduTeacherService;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 祥哥
 * @version 1.0
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("根据id查询")
    @GetMapping("/findById/{id}")
    public R findById(@PathVariable String id) {
        try {
//            int i = 1 / 0;
        } catch (Exception e) {
            throw new GuliException(20000, "自定义异常");
        }
        EduTeacher teacher = eduTeacherService.findById(id);
        return teacher != null ? R.ok().data("teacher", teacher) : R.error();
    }

    @ApiOperation("所有讲师列表")
    @GetMapping("/findAll")
    public R findAll() {
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        List<EduTeacher> list = eduTeacherService.list(wrapper);
        return R.ok().data("items",list);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/removeById/{id}")
    public Boolean removeById(@PathVariable String id) {
        return eduTeacherService.removeById(id);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        return save == false ? R.error() : R.ok();
    }

    @ApiOperation("修改讲师")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher teacher) {
        boolean update = eduTeacherService.updateById(teacher);
        return update == false ? R.error() : R.ok();
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("/pageSearch/{current}/{limit}")
    public R pageSearch(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        eduTeacherService.page(page, null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    @ApiOperation("多条件组合带分页")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageSearchCondition(@PathVariable Long current, @PathVariable Long limit,
                                 @RequestBody(required = false) TeacherQuery teacherQuery) {
        //1、创建Page对象
        Page<EduTeacher> page = new Page<>(current, limit);
        //2、创建wrapper条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //3、动态SQL
        eduTeacherService.query(wrapper, teacherQuery);
        eduTeacherService.page(page, wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }


}
