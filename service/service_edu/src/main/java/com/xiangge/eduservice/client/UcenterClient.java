package com.xiangge.eduservice.client;

import com.xiangge.educenter.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 祥哥
 * @version 1.0
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    @PostMapping("/educenter/member/getMemberInfoById/{memberId}")
    public UcenterMember getMemberInfoById(@PathVariable("memberId") String memberId);
}
