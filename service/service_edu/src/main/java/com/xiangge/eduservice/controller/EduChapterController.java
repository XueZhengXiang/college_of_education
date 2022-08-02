package com.xiangge.eduservice.controller;


import com.xiangge.commonutils.R;
import com.xiangge.eduservice.entity.EduChapter;
import com.xiangge.eduservice.entity.chapter.ChapterVo;
import com.xiangge.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        boolean save = chapterService.save(eduChapter);
        return save ? R.ok() : R.error();
    }

    //修改章节
    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter byId = chapterService.getById(chapterId);
        return R.ok().data("chapter", byId);
    }

    //修改章节
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        boolean update = chapterService.updateById(eduChapter);
        return update ? R.ok() : R.error();
    }

    //删除章节
    @DeleteMapping("/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        Boolean b = chapterService.deleteChapter(chapterId);
        return b ? R.ok() : R.error();
    }

}

































