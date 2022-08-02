package com.xiangge.educms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiangge.commonutils.R;
import com.xiangge.educms.entity.CrmBanner;
import com.xiangge.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-20
 */
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @GetMapping("/getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return R.ok().data("list", list);
    }


}

