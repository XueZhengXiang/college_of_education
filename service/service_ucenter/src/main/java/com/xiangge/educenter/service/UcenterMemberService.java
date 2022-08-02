package com.xiangge.educenter.service;

import com.xiangge.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiangge.educenter.entity.vo.UcenterLoginVo;
import com.xiangge.educenter.entity.vo.UcenterRegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-21
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterLoginVo member);

    void register(UcenterRegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
