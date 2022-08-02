package com.xiangge.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiangge.commonutils.R;
import com.xiangge.educms.entity.CrmBanner;
import com.xiangge.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("/pagBanner/{page}/{limit}")
    public R pagBanner(@PathVariable Long limit, @PathVariable Long page) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        crmBannerService.page(pageBanner, null);
        return R.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
    }

    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        boolean save = crmBannerService.save(crmBanner);
        return save ? R.ok() : R.error();
    }

    @GetMapping("/get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner get = crmBannerService.getById(id);
        return get != null ? R.ok().data("item", get) : R.error();
    }

    @PutMapping("/update")
    public R update(@RequestBody CrmBanner crmBanner) {
        boolean update = crmBannerService.updateById(crmBanner);
        return update ? R.ok() : R.error();
    }

    @DeleteMapping("/remove/{id}")
    public R delete(@PathVariable String id) {
        boolean b = crmBannerService.removeById(id);
        return b ? R.ok() : R.error();
    }

}

