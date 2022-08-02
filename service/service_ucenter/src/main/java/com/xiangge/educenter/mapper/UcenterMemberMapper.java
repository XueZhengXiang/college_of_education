package com.xiangge.educenter.mapper;

import com.xiangge.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-07-21
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    Integer countRegisterDay(String day);
}
