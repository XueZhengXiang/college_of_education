package com.xiangge.servicebase.exceptionhandle;

import com.xiangge.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 祥哥
 * @version 1.0
 * 一、异常处理
 * 1、特定异常----->2、全局异常
 * 3、自定义异常类
 *
 * 二、日志处理
 * 1、logback-spring.xml---->2、@Slf4j--->3、log.error(e.getMsg());
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    //指定出现异常执行哪个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行全局处理异常");
    }

    /**
     * 特定异常处理
     */

    @ExceptionHandler(ArithmeticException.class) //指定出现异常执行哪个方法
    @ResponseBody//为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行ArithmeticException异常");
    }

    /**
     * 自定义异常类:1、自定义异常类GuLiException；2、throw new
     */
    @ExceptionHandler(GuliException.class) //指定出现异常执行哪个方法
    @ResponseBody//为了返回数据
    public R error(GuliException e) {
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
