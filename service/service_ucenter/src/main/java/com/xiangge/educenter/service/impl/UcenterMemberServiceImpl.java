package com.xiangge.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiangge.commonutils.JwtUtils;
import com.xiangge.commonutils.MD5;
import com.xiangge.educenter.entity.UcenterMember;
import com.xiangge.educenter.entity.vo.UcenterLoginVo;
import com.xiangge.educenter.entity.vo.UcenterRegisterVo;
import com.xiangge.educenter.mapper.UcenterMemberMapper;
import com.xiangge.educenter.service.UcenterMemberService;
import com.xiangge.servicebase.exceptionhandle.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-21
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(UcenterLoginVo member) {
        String password = member.getPassword();
        String mobile = member.getMobile();
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(mobile)) {
            throw new GuliException(20001, "手机号和密码不能为空!!!");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember == null) {
            throw new GuliException(20001, "没有该号码的用户!!!");
        }
        if (!ucenterMember.getPassword().equals(MD5.encrypt(password))) {
            throw new GuliException(20001, "密码错误!!!");
        }
        if (ucenterMember.getIsDisabled()) {
            throw new GuliException(20001, "该用户被禁用!!!");
        }
        //前面判断成功后
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return jwtToken;
    }

    //注册
    @Override
    public void register(UcenterRegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "注册时手机号,密码,昵称,验证码都不能为空!!!");
        }
        //判断验证码
        Object redisCode = redisTemplate.opsForValue().get(mobile);
        if (!redisCode.equals(code)) {
            throw new GuliException(20001, "短信验证码失效或者不正确!!!");
        }
        //判断手机号是否重复,表里面有的不能重新注册
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new GuliException(20001, "该用户已存在不能重新注册!!!");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setMobile(mobile);
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        ucenterMember.setIsDisabled(false);
        baseMapper.insert(ucenterMember);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        return ucenterMember;
    }

    @Override
    public Integer countRegisterDay(String day) {
        Integer nums=baseMapper.countRegisterDay(day);
        return nums;
    }


}
