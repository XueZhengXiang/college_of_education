package com.xiangge.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 祥哥
 * @version 1.0
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "教师级别,模糊查询")
    private Integer level;
    @ApiModelProperty(value = "查询开使时间")
    private Date begin;
    @ApiModelProperty(value = "查询结束时间")
    private Date end;


}
