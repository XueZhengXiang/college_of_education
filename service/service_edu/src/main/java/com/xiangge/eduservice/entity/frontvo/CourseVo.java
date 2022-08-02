package com.xiangge.eduservice.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 祥哥
 * @version 1.0
 */
@Data
public class CourseVo {
    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "二级分类ID")
    private String subjectId;

    @ApiModelProperty(value = "一级分类级ID")
    private String subjectParentId;


    @ApiModelProperty(value = "销售排序")
    private String buyCountSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;

    @ApiModelProperty(value = "最新时间牌排序")
    private String gmtCreateSort;


}
