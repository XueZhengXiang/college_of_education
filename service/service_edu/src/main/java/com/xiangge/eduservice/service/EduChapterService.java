package com.xiangge.eduservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiangge.eduservice.entity.EduChapter;
import com.xiangge.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    Boolean deleteChapter(String chapterId);
}
