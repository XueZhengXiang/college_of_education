package com.xiangge.eduservice.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiangge.eduservice.entity.EduSubject;
import com.xiangge.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

     List<OneSubject>getAllOneTwoSubject();
}
