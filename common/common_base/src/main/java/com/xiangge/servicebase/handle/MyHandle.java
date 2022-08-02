package com.xiangge.servicebase.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 祥哥
 * @version 1.0
 * 实现填充逻辑,区别于mysql中的自动设置CURRENT_TIMESTAMP
 */
@Slf4j
@Component
public class MyHandle implements MetaObjectHandler {
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("Update自动填充");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("Insert自动填充");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
    }
}












