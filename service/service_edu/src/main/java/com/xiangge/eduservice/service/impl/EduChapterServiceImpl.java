package com.xiangge.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangge.eduservice.entity.EduChapter;
import com.xiangge.eduservice.entity.EduVideo;
import com.xiangge.eduservice.entity.chapter.ChapterVo;
import com.xiangge.eduservice.entity.chapter.VideoVo;
import com.xiangge.eduservice.mapper.EduChapterMapper;
import com.xiangge.eduservice.service.EduChapterService;
import com.xiangge.eduservice.service.EduVideoService;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapper);

        QueryWrapper<EduVideo> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapper2);

        List<ChapterVo> res = new ArrayList<>();

        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            res.add(chapterVo);
            List<VideoVo> videoList = new ArrayList<>();
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
        }

        return res;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        List<EduVideo> list = videoService.list(wrapper);
        if (list.size() > 0) {//查询出小节不删除
            throw new GuliException(20001, "查询到有小节,不能删除!");
        } else {
            int i = baseMapper.deleteById(chapterId);
            return i > 0;
        }
    }
}
