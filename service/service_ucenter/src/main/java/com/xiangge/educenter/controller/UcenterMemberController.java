package com.xiangge.educenter.controller;


import com.xiangge.commonutils.JwtUtils;
import com.xiangge.commonutils.R;
import com.xiangge.commonutils.ordervo.UcenterMemberOrder;
import com.xiangge.educenter.entity.UcenterMember;
import com.xiangge.educenter.entity.vo.UcenterLoginVo;
import com.xiangge.educenter.entity.vo.UcenterRegisterVo;
import com.xiangge.educenter.service.UcenterMemberService;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 登录表
 * </p>
 */
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("/login")
    public R login(@RequestBody UcenterLoginVo member) {
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    @PostMapping("/register")
    public R register(@RequestBody UcenterRegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //System.out.println(request);org.apache.catalina.connector.RequestFacade@7b9d68fb
        //第四步中把cookie中的token值放到request中
        //所以通过request中的token信息获得用户信息
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(id);
        return R.ok().data("userInfo", member);
    }

    @PostMapping("/educenter/member/getMemberInfoById/{memberId}")
    public UcenterMember getMemberInfoById(@PathVariable String memberId) {
        if (StringUtils.isEmpty(memberId)) {
            throw new GuliException(20001, "请登录！！！");
        }
        UcenterMember member = memberService.getById(memberId);
        return member;
    }

    @PostMapping("/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember memberServiceById = memberService.getById(id);
        UcenterMemberOrder memberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(memberServiceById, memberOrder);
        return memberOrder;
    }

    //查询某天注册人数
    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable String day) {
            Integer nums=memberService.countRegisterDay(day);
        return R.ok().data("countRegister",nums);
    }
}

