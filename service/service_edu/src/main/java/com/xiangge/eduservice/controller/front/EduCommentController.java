package com.xiangge.eduservice.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiangge.commonutils.JwtUtils;
import com.xiangge.commonutils.R;
import com.xiangge.educenter.entity.UcenterMember;
import com.xiangge.eduservice.client.UcenterClient;
import com.xiangge.eduservice.entity.EduComment;
import com.xiangge.eduservice.service.EduCommentService;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;

    //添加评论
    @PostMapping("/auth/addComment")
    public R addComment(HttpServletRequest request, @RequestBody EduComment eduComment) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            throw new GuliException(20001, "请先登录");
        }
        eduComment.setMemberId(memberId);
        //远程调用ucenter根据用户id获取用户信息
        UcenterMember memberVo = ucenterClient.getMemberInfoById(memberId);
        eduComment.setAvatar(memberVo.getAvatar());
        eduComment.setNickname(memberVo.getNickname());
        eduComment.setGmtCreate(memberVo.getGmtCreate());
        eduComment.setGmtModified(memberVo.getGmtModified());
        //保存评论
        commentService.save(eduComment);
        return R.ok();

    }

    //分页查询评论
    @GetMapping("/getCommentInfo/{page}/{limit}")
    public R getCommentInfo(@PathVariable Long page, @PathVariable Long limit, String courseId) {
        Page<EduComment> pageComment = new Page<>(page, limit);
        Map<String, Object> map = commentService.getCommentList(pageComment, courseId);
        return R.ok().data(map);
    }
    //评论


}

